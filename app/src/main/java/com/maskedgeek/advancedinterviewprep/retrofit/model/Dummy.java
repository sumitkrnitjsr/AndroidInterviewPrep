package com.maskedgeek.advancedinterviewprep.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dummy {

    // For serializing
    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    //Json response Key
    @SerializedName("imgUrl")
    public String imgUrl;

    @Override
    public String toString() {
        return name + " : " + imgUrl ;
    }
}
