package com.developer.crypto.di.modules;

import com.developer.crypto.connect.Api;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module()
public class ApiModule {

    @Provides
    @Singleton
    Api provideApi() {
        return new Api();
    }

}
