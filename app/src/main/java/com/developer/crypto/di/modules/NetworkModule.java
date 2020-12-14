package com.developer.crypto.di.modules;

import android.content.Context;
import com.developer.crypto.di.qualifiers.ApplicationContext;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module()
public class NetworkModule {
    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor(@ApplicationContext Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, @ApplicationContext Context context) {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

}
