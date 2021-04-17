package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency;

import android.content.Context;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.ConsumerApplication;

public class DependencyNetworkService {

    public String mAPI;
    public ConsumerApplication mContext;

    public DependencyNetworkService(ConsumerApplication context, String api){
        mContext = context;
        mAPI = api;
    }
}
