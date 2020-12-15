package com.developer.crypto.di.modules;

import android.app.Application;
import android.content.Context;

import com.developer.crypto.App;
import com.developer.crypto.di.modules.moduleTypes.ActivityModule;
import com.developer.crypto.di.modules.moduleTypes.FragmentModule;
import com.developer.crypto.di.qualifiers.ApplicationContext;
import com.developer.crypto.di.scopes.ActivityScope;
import com.developer.crypto.di.scopes.FragmentScope;
import com.developer.crypto.ui.activities.MainActivity;
import com.developer.crypto.ui.fragments.AssetDetailsFragment;
import com.developer.crypto.ui.fragments.CryptoListFragment;

import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module(includes = {RetrofitModule.class})
public abstract class ApplicationModule {

    @Provides
    @ApplicationContext
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Binds
    @Singleton
    public abstract Application application(App app);

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    abstract MainActivity mainActivityInjector();




    @FragmentScope
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract CryptoListFragment cryptoListFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract AssetDetailsFragment assetDetailsFragmentInjector();

}
