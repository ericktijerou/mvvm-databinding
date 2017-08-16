package erick.android.mvvm.data;


import java.util.List;

import erick.android.mvvm.data.model.BaseResponse;
import erick.android.mvvm.data.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by erick on 8/16/17.
 */

public interface RetrofitServiceInterface {
    @GET
    Observable<BaseResponse<List<User>>> getUsers(@Url String url);
}
