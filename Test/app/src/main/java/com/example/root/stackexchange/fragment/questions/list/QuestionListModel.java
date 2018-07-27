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
    private static final int MAX_PAGE_COUNT = 5;

    /**
     * a list of questions
     */
    private List<Question> questions = new ArrayList<>();

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
     * @return null if page count exceeded maximum else returns increased pag count by on.
     */
    @Nullable
    public Integer getNextPage(){
        if (pageCounter == MAX_PAGE_COUNT){
            return null;
        }

        return ++pageCounter;
    }

    public void setModel(QuestionList questionList){
        if (questionList != null){
            this.questions.addAll(questionList.getQuestions());
            this.hasMoreToLoad = questionList.getHasMore();
        }
    }

    public void resetModel(){
        this.questions.clear();
        this.pageCounter = 0;
        this.hasMoreToLoad = true;
    }
}