package com.example.root.stackexchange.service;


import com.example.root.stackexchange.backend.rest.endpoint.QuestionsEndpoint;
import com.example.root.stackexchange.backend.rest.model.QuestionList;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;

/**
 * service which will be used by view model to achieve backend calls.
 */
public class StackExchangeService {

    final private QuestionsEndpoint questionsEndpoint;

    @Inject
    public StackExchangeService(QuestionsEndpoint questionsEndpoint) {
        this.questionsEndpoint = questionsEndpoint;
    }

    /**
     * gets a list of questions.
     *
     * @param pageNumber number of requested page.
     * @return
     */
    public Single<Result<QuestionList>> getQuestions(int pageNumber) {
        return questionsEndpoint.getQuestions(String.valueOf(pageNumber));
    }

    public boolean isResultFromCache(Result<QuestionList> result) {
        return result.response() != null && result.response().raw() !=null && result.response().raw().cacheResponse() != null;
    }
}