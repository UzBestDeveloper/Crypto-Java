package com.developer.crypto.connect;

import com.developer.crypto.connect.models.AssetObject;
import com.developer.crypto.connect.response.ResponseBody;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitRestApi {

    @GET("v2/assets")
    Observable<Response<ResponseBody<List<AssetObject>>>> getAssets(@Query("page") long page);

    @GET("v1/assets/{assetKey}/metrics/price/time-series")
    Observable<Response<ResponseBody<TimeSeriesResponse>>> getTimeSeries(@Path("assetKey") String assetKey,@Query("beg") String beg,@Query("end") String end);

}
