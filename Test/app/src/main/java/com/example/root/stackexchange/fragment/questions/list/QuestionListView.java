package com.example.root.stackexchange.fragment.questions.list;

import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.fragment.base.BaseView;

import java.util.List;

public interface QuestionListView extends BaseView {
    /**
     * called after first load or after reload.
     *
     * @param questions   questions loaded.
     * @param isCacheData true if data is from cache.
     */
    void show(List<Question> questions, boolean isCacheData);

    /**
     * called when more questions is loaded
     *
     * @param questions new arrived questions.
     */
    void moreQuestionsLoaded(List<Question> questions);
}
