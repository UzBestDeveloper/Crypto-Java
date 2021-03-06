package com.developer.crypto.mvp.views.fragments;

import com.developer.crypto.connect.models.AssetObject;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface CryptoListFragmentView extends MvpView {

    void initAssets(List<AssetObject> assets);

    void stopProgress();
}
