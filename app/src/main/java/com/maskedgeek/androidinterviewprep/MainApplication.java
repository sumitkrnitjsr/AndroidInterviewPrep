package com.maskedgeek.androidinterviewprep;

import android.app.Application;
import android.util.Log;

// Extend Application class and define in Manifest file to run android:name=".MainApplication"
// To check app with only manifest installed, use " adb shell pm list packages | grep -i "interview" "

public class MainApplication extends Application {

    private String TAG = MainApplication.class.getSimpleName();

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, " onCreate ");
        Log.d(TAG,  " onCreate on Thread = " + Thread.currentThread().getId());
        // Later inject Dependencies
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        Log.d(TAG, " onLowMemory ");
        // Later inject Dependencies
    }

    @Override
    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callbacks){
        Log.d(TAG, " registerActivityLifecycleCallbacks ");
    }


}
