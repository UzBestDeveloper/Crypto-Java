package com.developer.crypto.mvp.presenters.activities;

import android.os.CountDownTimer;

import com.developer.crypto.App;
import com.developer.crypto.connect.ApiManager;
import com.developer.crypto.mvp.views.activities.MainActivityView;

import javax.inject.Inject;
import moxy.InjectViewState;
import moxy.MvpPresenter;


@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    @Inject
    ApiManager mApiManager;

    private MainActivityView mView;

    public MainActivityPresenter() {
        super();
        injectData();
        mView = getViewState();
    }

    private void injectData() {
        App.getComponent().inject(this);
    }


}
