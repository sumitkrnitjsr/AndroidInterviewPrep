package com.maskedgeek.advancedinterviewprep.retrofit.data.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DummyRequest {

    @Expose
    @SerializedName("id")
    public String id;

    public DummyRequest(String id) {
            this.id = id;
    }
}
