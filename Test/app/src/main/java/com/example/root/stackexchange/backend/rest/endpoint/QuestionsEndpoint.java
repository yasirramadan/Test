package com.example.root.stackexchange.backend.rest.endpoint;

import com.example.root.stackexchange.backend.interceptor.QuestionsDefaultHeadersInterceptor;
import com.example.root.stackexchange.backend.rest.model.QuestionList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * for this endpoint there is default headers defined in: @see {@link QuestionsDefaultHeadersInterceptor}
 */
public interface QuestionsEndpoint {
    /**
     * gets a list of questions by page number.
     */
    @GET("questions")
    Single<Response<QuestionList>> getQuestions(@Query(value="page") String pageNumber);
}