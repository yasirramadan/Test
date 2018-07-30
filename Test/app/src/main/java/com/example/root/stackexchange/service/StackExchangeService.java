package com.example.root.stackexchange.service;


import android.support.annotation.NonNull;

import com.example.root.stackexchange.R;
import com.example.root.stackexchange.backend.exeption.BackendException;
import com.example.root.stackexchange.backend.rest.endpoint.QuestionsEndpoint;
import com.example.root.stackexchange.backend.rest.model.Questions;
import com.example.root.stackexchange.util.NetworkUtils;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

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
    public Single<Response<Questions>> getQuestions(int pageNumber) {
        return questionsEndpoint.getQuestions(String.valueOf(pageNumber));
    }

    /**
     * tests if response is from network or cache.
     *
     * @param response
     * @return true if the response from cache
     */
    public boolean isResultFromCache(Response response) {
        return response != null && response.raw() != null && response.raw().networkResponse() == null;
    }

    /**
     * gets message resource id from throwable
     *
     * @param throwable
     * @return resource id
     */
    @NonNull
    public Integer getErrorMessage(Throwable throwable) {
        BackendException.Kind kind = NetworkUtils.getExceptionKind(throwable);
        switch (kind) {
            case HTTP:
                return R.string.server_error;
            case NETWORK:
                return R.string.network_error;
            default:
                return R.string.general_error;
        }
    }
}