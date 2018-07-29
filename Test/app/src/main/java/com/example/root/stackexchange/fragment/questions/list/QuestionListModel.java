package com.example.root.stackexchange.fragment.questions.list;

import com.example.root.stackexchange.backend.rest.model.Question;
import com.example.root.stackexchange.backend.rest.model.QuestionList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * This class is just example of model classes.
 */
public class QuestionListModel implements Serializable {
    /**
     * a list of questions
     */
    private List<Question> questions = new ArrayList<>();

    /**
     * page counter.
     */
    private Integer pageCounter = 0;

    private Boolean hasMoreToLoad = true;

    public List<Question> getQuestions() {
        return questions;
    }

    public Integer getPageCounter() {
        return pageCounter;
    }

    public Boolean hasMoreToLoad() {
        return hasMoreToLoad;
    }

    /**
     * @return increased pag count by one.
     */
    @Nullable
    public Integer getNextPage() {
        return ++pageCounter;
    }

    /**
     * initializes model called when we visit fragment for first time or when reload.
     *
     * @param questionList
     */
    public void initModel(QuestionList questionList) {
        if (questionList != null) {
            this.questions = questionList.getQuestions();
            this.hasMoreToLoad = questionList.getHasMore();
        }
    }

    /**
     * appends more questions to question list.
     *
     * @param questions
     */
    public void addMoreQuestions(List<Question> questions) {
        this.questions.addAll(questions);
    }

    /**
     * resets counter.
     */
    public void resetPageCounter() {
        this.pageCounter = 0;
    }
}