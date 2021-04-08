package com.maskedgeek.androidinterviewprep.services;

import android.os.Binder;
import android.os.IBinder;

// To return Service object to calling remote process
public class RemoteBinder extends Binder {

    private BackgroundService service;

    public RemoteBinder(BackgroundService service){
        this.service = service;
    }

    BackgroundService getService(){
        return service;
    }
}
