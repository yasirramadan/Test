package com.example.root.test.service;

//TODO: service should be injected by dagger per fragment

import io.reactivex.Single;

/**
 * service which will be used by view model to achieve backend calls
 */
public class ExampleService {
    public Single<Object> getExample() {
        return Single.just(new Object());
    }
}
