package com.developer.crypto.mvp.presenters.fragments;

import com.developer.crypto.App;
import com.developer.crypto.connect.ApiManager;
import com.developer.crypto.connect.models.AssetObject;
import com.developer.crypto.connect.response.ResponseBody;
import com.developer.crypto.mvp.views.fragments.CryptoListFragmentView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class CryptoListFragmentPresenter extends MvpPresenter<CryptoListFragmentView> {

    @Inject
    ApiManager mApiManager;

    private CryptoListFragmentView mView;

    public CryptoListFragmentPresenter() {
        super();
        injectData();
        mView = getViewState();
    }

    private void injectData() {
        App.getComponent().inject(this);
    }


    public void GET_ASSETS(long page) {
        mApiManager.getAssets(page)
                .subscribe(new Observer<Response<ResponseBody<List<AssetObject>>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody<List<AssetObject>>> response) {
                        if (null != response.body() && null != response.body().getData() && response.body().getData().size() > 0) {
                            mView.initAssets(response.body().getData());
                        }
                        mView.stopProgress();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}