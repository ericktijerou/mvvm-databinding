package erick.android.mvvm.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by erick on 8/8/17.
 */

public class Info implements Serializable {
    @SerializedName("seed")
    private String seed;
    @SerializedName("results")
    private int results;
    @SerializedName("page")
    private int page;
    @SerializedName("version")
    private String version;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

