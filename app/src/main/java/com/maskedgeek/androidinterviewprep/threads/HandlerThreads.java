package com.maskedgeek.androidinterviewprep.threads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class HandlerThreads extends HandlerThread{

    Handler mHandler;

    public HandlerThreads(String name) {
        super(name);
    }

    @Override
    public void onLooperPrepared(){
        mHandler = new Handler(getLooper()){
            @Override
            public void handleMessage(Message msg){

            }
        };
    }
}
