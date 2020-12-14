package com.developer.crypto;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;
import com.developer.crypto.di.components.ApplicationComponent;
import com.developer.crypto.di.components.DaggerApplicationComponent;
import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class App extends MultiDexApplication implements HasAndroidInjector {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    @Inject
    DispatchingAndroidInjector<Object> mInjector;

    private static ApplicationComponent mApplicationComponent;

    public void injectData() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .build();
        mApplicationComponent.inject(this);
    }

    public static ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    private void initApplication() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        injectData();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return mInjector;
    }
}
