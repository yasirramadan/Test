package com.example.root.stackexchange.backend.exeption;

import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BackendException extends RuntimeException {

    public static BackendException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new BackendException(message, url, response, Kind.HTTP, null, retrofit);
    }

    public static BackendException networkError(IOException exception) {
        return new BackendException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    public static BackendException unexpectedError(Throwable exception) {
        return new BackendException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    /**
     * Identifies the event kind which triggered a {@link BackendException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;

    private BackendException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    public Kind getKind() {
        return kind;
    }

    /**
     * HTTP response code.
     */
    public int getCode() {
        if (response == null) {
            return 0;
        }
        return response.code();
    }

    /**
     * The Retrofit this request was executed on.
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no response or deserialization fails.
     * Can be called only once.
     */
    @Nullable
    private <T> T getErrorBodyAs(Class<T> type) {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        try {
            return converter.convert(response.errorBody());
        } catch (IOException e) {
            // Unable to convert
            return null;
        }
    }
}
