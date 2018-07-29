package com.example.root.stackexchange.fragment.questions.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.fragment.base.BaseViewModel;

import javax.inject.Inject;

import pocketknife.PocketKnife;
import pocketknife.SaveState;

public class QuestionDetailViewModel extends BaseViewModel<QuestionDetailView> {
    public static final String ARG_QUESTION = "arg_question";

    @SaveState
    QuestionDetailModel model;


    @Inject
    QuestionDetailViewModel() {
    }

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            PocketKnife.restoreInstanceState(arguments, savedInstanceState);
        } else {
            Question question;
            if (arguments != null) {
                question = (Question) arguments.getSerializable(ARG_QUESTION);
            } else {
                throw new IllegalArgumentException("cannot proceed without city name");
            }

            if (model == null) {
                model = new QuestionDetailModel(question);
            }
        }
    }

    @Override
    public void onBindView(@NonNull QuestionDetailView view) {
        super.onBindView(view);
        if (getView() != null){
            getView().show(model.getQuestionDetail().get());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PocketKnife.saveInstanceState(this, bundle);
    }

    public QuestionDetailModel getModel() {
        return model;
    }
}