package com.example.root.stackexchange.backend.exeption;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory(RxJava2CallAdapterFactory original) {
        this.original = original;
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static CallAdapter.Factory create(RxJava2CallAdapterFactory original) {
        return new RxErrorHandlingCallAdapterFactory(original);
    }

    @Override
    public CallAdapter get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    public static class RxCallAdapterWrapper implements CallAdapter {
        private final Retrofit retrofit;
        private final CallAdapter wrapped;

        RxCallAdapterWrapper(Retrofit retrofit, CallAdapter wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object adapt(Call call) {
            Object adaptedCall = wrapped.adapt(call);

            if (adaptedCall instanceof Completable) {
                return ((Completable) adaptedCall).onErrorResumeNext(
                        throwable -> Completable.error(asBackendException(throwable)));
            }

            if (adaptedCall instanceof Single) {
                return ((Single) adaptedCall).onErrorResumeNext(
                        throwable -> Single.error(asBackendException((Throwable) throwable)));
            }

            if (adaptedCall instanceof Observable) {
                return ((Observable) adaptedCall).onErrorResumeNext(
                        (Function<? super Throwable, ? extends ObservableSource>)
                                throwable -> Observable.error(asBackendException(throwable)));
            }

            throw new RuntimeException("Observable Type not supported");
        }

        private BackendException asBackendException(Throwable throwable) {
            BackendException backendException;

            if (throwable instanceof HttpException) {
                // We had non-200 http error
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                backendException = BackendException.httpError(response.raw().request().url().toString(), response, retrofit);
            } else if (throwable instanceof IOException) {
                // A network error happened
                backendException = BackendException.networkError((IOException) throwable);
            } else {
                // We don't know what happened. We need to simply convert to an unknown error
                backendException = BackendException.unexpectedError(throwable);
            }

            return backendException;
        }
    }
}