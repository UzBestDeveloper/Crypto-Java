package com.developer.crypto.ui.activities;

import android.os.Bundle;

import com.developer.crypto.databinding.ActivityMainBinding;
import com.developer.crypto.mvp.presenters.activities.MainActivityPresenter;
import com.developer.crypto.mvp.views.activities.MainActivityView;

import moxy.presenter.InjectPresenter;

public class MainActivity extends BasicActivity implements MainActivityView {

    ActivityMainBinding binding;

    @InjectPresenter
    MainActivityPresenter mPresenter;

    private void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }


}