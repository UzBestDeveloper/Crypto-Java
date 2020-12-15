package com.developer.crypto.connect;

import com.developer.crypto.connect.models.AssetObject;
import com.developer.crypto.connect.response.ResponseBody;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@Singleton
public class ApiManager {

    private RetrofitRestApi mRetrofitRestApi;

    public ApiManager() {
    }

    @Inject
    public ApiManager(RetrofitRestApi mRetrofitRestApi) {
        this.mRetrofitRestApi = mRetrofitRestApi;
    }


    public Observable<Response<ResponseBody<List<AssetObject>>>> getAssets(long page) {
        return mRetrofitRestApi.getAssets(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Response<ResponseBody<TimeSeriesResponse>>> getTimeSeries(String assetKey,String end, String beg) {
        return mRetrofitRestApi.getTimeSeries(assetKey,beg,end)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
