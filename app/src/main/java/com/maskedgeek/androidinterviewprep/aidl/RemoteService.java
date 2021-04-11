package com.maskedgeek.androidinterviewprep.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.maskedgeek.androidinterviewprep.services.RemoteBinder;
import com.maskedgeek.androidinterviewprep.services.ServiceHandler;

import aidl.IRemoteService;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

// need exported = true if to be bound from separate application
// service needs to be running in order to be connected

public class RemoteService extends Service {

    private String TAG = RemoteService.class.getSimpleName();


    // define aidl interface file and mention dir src in app build.gradle
    private final IRemoteService.Stub binder = new IRemoteService.Stub(){
        public int getPid(){
            Log.d(TAG , " LocalService in Process Pid = " + Process.myPid() + "Thread = " + Thread.currentThread().getId());
            return (int)Process.myPid();
        }
    };

    @Override
    public void onCreate(){
        Log.d(TAG, " onCreate  on thread = " + Thread.currentThread().getId());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Return Binder here to allow remote to access service object
        Log.d(TAG,  " onBind ");
        return binder;
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

    // START A NEW THREAD HERE TO AVOID MAIN THREAD OPERATIONS ANR
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(TAG,  " onStartCommand on Thread = " + Thread.currentThread().getId());
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){
        Log.d(TAG,  " onDestroy ");
    }

}
