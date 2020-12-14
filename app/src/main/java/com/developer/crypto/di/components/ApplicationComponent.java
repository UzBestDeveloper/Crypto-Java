package com.developer.crypto.di.components;

import com.developer.crypto.App;
import com.developer.crypto.di.modules.ApplicationModule;
import com.developer.crypto.mvp.presenters.activities.MainActivityPresenter;
import com.developer.crypto.mvp.presenters.fragments.CryptoListFragmentPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    void inject(App app);

    void inject(MainActivityPresenter mainActivityPresenter);


    void inject(CryptoListFragmentPresenter cryptoListFragmentPresenter);

    @Component.Builder
    interface Builder {
        @BindsInstance

        Builder application(App app);

        ApplicationComponent build();
    }
}
