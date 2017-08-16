package erick.android.mvvm;

import android.app.Application;
import android.content.Context;

import erick.android.mvvm.data.ApiClientRetrofit;
import erick.android.mvvm.data.RetrofitServiceInterface;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by erick on 8/16/17.
 */

public class MVVMApplication extends Application {

    private RetrofitServiceInterface retrofitServiceInterface;
    private Scheduler scheduler;

    private static MVVMApplication get(Context context) {
        return (MVVMApplication) context.getApplicationContext();
    }

    public static MVVMApplication create(Context context) {
        return MVVMApplication.get(context);
    }

    public RetrofitServiceInterface getRetrofitServiceInterface() {
        if (retrofitServiceInterface == null) {
            retrofitServiceInterface = ApiClientRetrofit.getApiClient();
        }

        return retrofitServiceInterface;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setRetrofitServiceInterface(RetrofitServiceInterface retrofitServiceInterface) {
        this.retrofitServiceInterface = retrofitServiceInterface;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
