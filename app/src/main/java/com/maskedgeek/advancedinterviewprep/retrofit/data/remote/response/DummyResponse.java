package com.maskedgeek.advancedinterviewprep.retrofit.data.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.maskedgeek.advancedinterviewprep.retrofit.model.Dummy;

import java.util.List;

public class DummyResponse {

    @Expose
    @SerializedName("statusCode")
    public String statusCode;

    @Expose
    @SerializedName("message")
    public String message;

    @Expose
    @SerializedName("data")
    public List<Dummy> data;

}
