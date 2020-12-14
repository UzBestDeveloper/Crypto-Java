package com.developer.crypto.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import dagger.android.AndroidInjection;
import moxy.MvpAppCompatActivity;

@SuppressLint("Registered")
public class BasicActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
