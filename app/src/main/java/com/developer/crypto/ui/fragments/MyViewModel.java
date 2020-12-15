package com.developer.crypto.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.developer.crypto.connect.models.AssetObject;

import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<AssetObject>> assets = new MutableLiveData<>();
    private MutableLiveData<Integer> pageNumber = new MutableLiveData<>();

    public void saveAssets(List<AssetObject> item) {
        assets.setValue(item);
    }


    public LiveData<List<AssetObject>> getAssets() {
        return assets;
    }

    public void savePageNumber(int page) {
        pageNumber.setValue(page);
    }

    public Integer getPageNumber() {
        return pageNumber.getValue();
    }

}
