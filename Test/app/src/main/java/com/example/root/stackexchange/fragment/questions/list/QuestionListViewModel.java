package com.example.root.stackexchange.fragment.questions.list;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.stackexchange.backend.rest.model.QuestionList;
import com.example.root.stackexchange.fragment.base.BaseViewModel;
import com.example.root.stackexchange.service.StackExchangeService;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pocketknife.PocketKnife;
import pocketknife.SaveState;

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

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        }

        if (model == null) {
            model = new QuestionListModel();
        }
    }

    @Override
    public void onBindView(@NonNull QuestionListView view) {
        super.onBindView(view);
       if (model.getQuestions().size() == 0) {
           load(model.getNextPage(), true);
        } else {
            if (getView() != null) {
                getView().show(model.getQuestions(), false);
            }
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    private  Single<retrofit2.adapter.rxjava2.Result<QuestionList>> getQuestions(int page, boolean showLoading){
        return  stackExchangeService.getQuestions(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    if (getView() != null && showLoading){
                        getView().showLoading();
                    }
                }).doFinally(()->{
                    if (getView() != null && showLoading){
                        getView().dismissLoading();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void load(int pageNum, boolean showLoading){
        getQuestions(pageNum, showLoading).subscribe(questionList -> {
            if (questionList != null){
                //TODO: setData
                final boolean isCacheData = stackExchangeService.isResultFromCache(questionList);
                if (getView() != null){
                    model.setModel(questionList.response().body());
                    getView().show(model.getQuestions(), isCacheData);
                }
            }
        }, throwable -> {

        });
    }

    @SuppressLint("CheckResult")
    public void loadMore() {
        if (model.getNextPage() == null){
            getView().moreQuestionsLoaded(new ArrayList<>());
            return;
        }

        if (model.hasMoreToLoad()){
            getQuestions(model.getPageCounter(), false).subscribe(questionList -> {
                if (questionList != null){
                    if (getView() != null){
                        model.setModel(questionList.response().body());
                        getView().moreQuestionsLoaded(questionList.response().body().getQuestions());
                    }
                }
            }, throwable -> {

            });
        }
    }

    public void reload(){
        model.resetModel();
        load(model.getNextPage(), false);
    }
}