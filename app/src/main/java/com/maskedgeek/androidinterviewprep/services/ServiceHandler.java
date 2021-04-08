package com.maskedgeek.androidinterviewprep.services;

import android.app.Service;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ServiceHandler extends Handler {

    private Service listener;
    private String TAG = ServiceHandler.class.getSimpleName();

    public ServiceHandler(Looper looper, Service service){
        super(looper);
        listener = service;
    }

    @Override
    public void handleMessage(Message msg){
        if(msg == null){
            return;
        }
        Log.d(TAG, " handleMessage " + msg.what + " onThread = " + Thread.currentThread().getId());

        // listener.stopSelf();
    }

}
