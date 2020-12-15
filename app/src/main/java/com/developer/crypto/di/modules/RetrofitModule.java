package com.developer.crypto.di.modules;

import android.annotation.SuppressLint;

import com.developer.crypto.connect.Api;
import com.developer.crypto.connect.RetrofitRestApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module(includes = {NetworkModule.class,ApiModule.class})
public class RetrofitModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(Api api, HttpLoggingInterceptor httpLoggingInterceptor) {

        return new Retrofit.Builder()
                .baseUrl(api.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getUnsafeOkHttpClient(httpLoggingInterceptor))
                .build();
    }

    @Provides
    @Singleton
    RetrofitRestApi provideRetrofitRestApi(Retrofit retrofit) {
        return retrofit.create(RetrofitRestApi.class);
    }


    private static OkHttpClient getUnsafeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,
                                                       String authType) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,
                                                       String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true).build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
