package com.developer.crypto.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.developer.crypto.databinding.FragmentCryptoListBinding;
import com.developer.crypto.mvp.presenters.fragments.CryptoListFragmentPresenter;
import com.developer.crypto.mvp.views.fragments.CryptoListFragmentView;

import org.jetbrains.annotations.NotNull;

import moxy.presenter.InjectPresenter;

public class CryptoListFragment extends BasicFragment implements CryptoListFragmentView {

    FragmentCryptoListBinding binding;

    @InjectPresenter
    CryptoListFragmentPresenter mPresenter;

    public CryptoListFragment() {
        // Required empty public constructor
    }

    private void initView() {

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCryptoListBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        initView();
        return view;
    }

}