package com.developer.crypto.connect;


import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiManager {

    private RetrofitRestApi mRetrofitRestApi;

    public ApiManager() {
    }

    @Inject
    public ApiManager(RetrofitRestApi mRetrofitRestApi) {
        this.mRetrofitRestApi = mRetrofitRestApi;
    }

//
//    public Observable<Response<LoginResponse>> login(RequestBody requestBody) {
//        return mRetrofitRestApi.login(requestBody)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

}
