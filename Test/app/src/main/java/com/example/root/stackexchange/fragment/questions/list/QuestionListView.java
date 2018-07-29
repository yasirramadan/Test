package com.example.root.stackexchange.fragment.questions.list;

import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.fragment.base.BaseView;

import java.util.List;

public interface QuestionListView extends BaseView {
    void show(List<Question> questions, boolean isCacheData);

    void moreQuestionsLoaded(List<Question> questions);
}
