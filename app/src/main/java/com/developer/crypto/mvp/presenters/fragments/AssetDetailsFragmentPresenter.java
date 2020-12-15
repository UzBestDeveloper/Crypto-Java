package com.developer.crypto.mvp.presenters.fragments;

import com.developer.crypto.App;
import com.developer.crypto.connect.ApiManager;
import com.developer.crypto.connect.TimeSeriesResponse;
import com.developer.crypto.connect.response.ResponseBody;
import com.developer.crypto.mvp.views.fragments.AssetDetailsFragmentView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import retrofit2.Response;

@InjectViewState
public class AssetDetailsFragmentPresenter extends MvpPresenter<AssetDetailsFragmentView> {

    @Inject
    ApiManager mApiManager;

    private AssetDetailsFragmentView mView;

    public AssetDetailsFragmentPresenter() {
        super();
        injectData();
        mView = getViewState();
    }

    private void injectData() {
        App.getComponent().inject(this);
    }

    public void GET_TIME_SERIES(String assetKey,String end, String beg) {

        mApiManager.getTimeSeries(assetKey,end,beg)
                .subscribe(new Observer<Response<ResponseBody<TimeSeriesResponse>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody<TimeSeriesResponse>> response) {
                        if (response.isSuccessful() && null != response.body() && null != response.body().getData().getValues()) {
                            mView.initTimeSeries(response.body().getData());
                        }
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
