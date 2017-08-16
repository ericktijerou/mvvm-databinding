package erick.android.mvvm.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by erick on 8/16/17.
 */

public class ApiClientRetrofit {
    private static RetrofitServiceInterface servicesApiInterface;
    public final static String BASE_URL = "http://api.randomuser.me/?results=5";
    public static RetrofitServiceInterface getApiClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RetrofitServiceInterface.class);
    }
}

