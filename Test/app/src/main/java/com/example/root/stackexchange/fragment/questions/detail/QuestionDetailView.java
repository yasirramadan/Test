package com.example.root.stackexchange.fragment.questions.detail;


import com.example.root.stackexchange.fragment.base.BaseView;

public interface QuestionDetailView extends BaseView {
    /**
     * @param questionBody body of question.
     */
    void show(String questionBody);
}
