package com.maskedgeek.androidinterviewprep.services;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

// Register in Manifest

public class BackgroundService extends Service {

    private String TAG  = BackgroundService.class.getSimpleName();
    private Looper mLooper;
    private ServiceHandler mServiceHandler;
    private RemoteBinder mRemoteBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Return Binder here to allow remote to access service object
        Log.d(TAG,  " onBind ");
        mRemoteBinder = new RemoteBinder(this);
        return mRemoteBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG,  " onRebind ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,  " onRebind ");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,  " onCreate ");
        HandlerThread handlerThread = new HandlerThread("BackgroundServiceThread", THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        mLooper = handlerThread.getLooper();
        mServiceHandler = new ServiceHandler(mLooper, this);
    }

    // START A NEW THREAD HERE TO AVOID MAIN THREAD OPERATIONS ANR
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(TAG,  " onStartCommand on Thread = " + Thread.currentThread().getId());
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.d(TAG,  " onDestroy ");
    }

    public void showToast(){
        Toast.makeText(getApplicationContext(), " Toast from Binder ", Toast.LENGTH_SHORT).show();
    }

}
