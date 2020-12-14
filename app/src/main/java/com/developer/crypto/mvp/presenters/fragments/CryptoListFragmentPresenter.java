package com.developer.crypto.mvp.presenters.fragments;

import com.developer.crypto.App;
import com.developer.crypto.connect.ApiManager;
import com.developer.crypto.mvp.views.fragments.CryptoListFragmentView;
import javax.inject.Inject;
import moxy.InjectViewState;
import moxy.MvpPresenter;

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


}
