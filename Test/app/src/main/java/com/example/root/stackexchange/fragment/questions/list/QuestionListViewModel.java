package com.example.root.stackexchange.fragment.questions.list;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.stackexchange.R;
import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.backend.rest.model.QuestionList;
import com.example.root.stackexchange.fragment.base.BaseViewModel;
import com.example.root.stackexchange.service.StackExchangeService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pocketknife.PocketKnife;
import pocketknife.SaveState;
import retrofit2.Response;

public class QuestionListViewModel extends BaseViewModel<QuestionListView> {
    private final StackExchangeService stackExchangeService;

    @SaveState
    QuestionListModel model;

    @Inject
    QuestionListViewModel(StackExchangeService stackExchangeService) {
        this.stackExchangeService = stackExchangeService;
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null && arguments != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        } else {
            if (model == null) {
                model = new QuestionListModel();
            }
        }

    }

    @Override
    public void onBindView(@NonNull QuestionListView view) {
        super.onBindView(view);
        if (model.getQuestions().size() == 0) {
            //we have no data so load data/
            load(model.getNextPage(), true);
        } else {
            //we already have data so show it.
            requestShow(false);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    /**
     * gets questions from server.
     *
     * @param page        page number.
     * @param showLoading whether to show loading or not.
     * @return
     */
    private Single<Response<QuestionList>> getQuestions(int page, boolean showLoading) {
        return stackExchangeService.getQuestions(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (getView() != null && showLoading) {
                        getView().showLoading();
                    }
                }).doOnError(throwable -> {
                    int errorResId = stackExchangeService.getErrorMessage(throwable);
                    if (getView() != null) {
                        getView().showEmptyView(errorResId);
                    }
                }).doFinally(() -> {
                    if (getView() != null && showLoading) {
                        getView().dismissLoading();
                    }
                });
    }

    /**
     * loads data .
     *
     * @param pageNum
     * @param showLoading
     */
    @SuppressLint("CheckResult")
    private void load(int pageNum, boolean showLoading) {
        getQuestions(pageNum, showLoading).subscribe(questionList -> {
            if (questionList != null) {
                model.initModel(questionList.body());

                boolean isError = !questionList.isSuccessful();
                if (isError) {
                    requestShowEmptyView();
                } else {
                    boolean isFromCache = stackExchangeService.isResultFromCache(questionList);
                    requestShow(isFromCache);
                }
            }
        }, throwable -> {

        });
    }

    /**
     * loads more questions.
     */
    @SuppressLint("CheckResult")
    public void loadMore() {
        //no more questions pass empty arrayList
        if (!model.hasMoreToLoad()) {
            requestMoreQuestionsLoaded(new ArrayList());
            return;
        }

        //has more questions so load.
        getQuestions(model.getPageCounter(), false).subscribe(questionList -> {
            if (questionList != null) {
                List<Question> questions = questionList.body() != null ? questionList.body().getQuestions() : new ArrayList<>();
                model.addMoreQuestions(questions);
                requestMoreQuestionsLoaded(questions);
            }
        }, throwable -> {
        });
    }

    /**
     * reloads question.
     */
    public void reload() {
        //reset page counter to start from the beginning.
        model.resetPageCounter();
        load(model.getNextPage(), false);
    }

    private void requestShow(boolean isCacheData) {
        if (getView() != null) {
            getView().show(model.getQuestions(), isCacheData);
        }
    }

    private void requestShowEmptyView() {
        if (getView() != null) {
            getView().showEmptyView(R.string.general_error);
        }
    }

    private void requestMoreQuestionsLoaded(List<Question> questions) {
        if (getView() != null) {
            getView().moreQuestionsLoaded(questions);
        }
    }
}