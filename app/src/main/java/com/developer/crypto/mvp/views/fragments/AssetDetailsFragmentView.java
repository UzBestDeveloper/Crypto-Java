package com.developer.crypto.mvp.views.fragments;

import com.developer.crypto.connect.TimeSeriesResponse;

import moxy.MvpView;
import moxy.viewstate.strategy.SingleStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@StateStrategyType(SingleStateStrategy.class)
public interface AssetDetailsFragmentView extends MvpView {

    void initTimeSeries(TimeSeriesResponse data);
}
