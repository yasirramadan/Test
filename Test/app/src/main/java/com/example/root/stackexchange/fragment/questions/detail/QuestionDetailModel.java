package com.example.root.stackexchange.fragment.questions.detail;

import android.databinding.ObservableField;

import com.example.root.stackexchange.backend.rest.model.Question;

import java.io.Serializable;

public class QuestionDetailModel implements Serializable {
    /**
     * Question title
     */
    private final ObservableField<String> questionTitle = new ObservableField<>();

    /**
     * Question detail
     */
    private final ObservableField<String> questionDetail = new ObservableField<>();

    public QuestionDetailModel(Question question) {
        this.questionTitle.set(question.getTitle());
        this.questionDetail.set(question.getBody());
    }

    public ObservableField<String> getQuestionTitle() {
        return questionTitle;
    }

    public ObservableField<String> getQuestionDetail() {
        return questionDetail;
    }
}
