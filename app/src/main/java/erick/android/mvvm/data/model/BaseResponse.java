package erick.android.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by erick on 8/8/17.
 */

public class BaseResponse<T>  {
    @SerializedName("results")
    private T results;
    @SerializedName("info")
    private Info info;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

