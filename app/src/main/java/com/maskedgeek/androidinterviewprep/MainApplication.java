package com.maskedgeek.androidinterviewprep;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.maskedgeek.advancedinterviewprep.retrofit.ApplicationComponent3;
import com.maskedgeek.advancedinterviewprep.retrofit.ApplicationModule3;
import com.maskedgeek.advancedinterviewprep.retrofit.DaggerApplicationComponent3;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.DatabaseService;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.ui.ApplicationComponent2;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.ui.DaggerApplicationComponent2;


import javax.inject.Inject;

import leakcanary.AppWatcher;

// Extend Application class and define in Manifest file to run android:name=".MainApplication"
// To check app with only manifest installed, use " adb shell pm list packages | grep -i "interview" "

public class MainApplication extends Application {

    private String TAG = MainApplication.class.getSimpleName();
    public  ApplicationComponent2 applicationComponent;
    public ApplicationComponent3 applicationComponent3;

    @Inject
    public DatabaseService databaseService;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, " onCreate ");
        Log.d(TAG,  " onCreate on Thread = " + Thread.currentThread().getId());
        // Later inject Dependencies
        applicationComponent = DaggerApplicationComponent2.builder().
              applicationModule(new com.maskedgeek.advancedinterviewprep.rxroomdagger.ui.ApplicationModule(this)).build();
        applicationComponent.inject(this);

        applicationComponent3 = DaggerApplicationComponent3.builder().
                applicationModule3(new ApplicationModule3(this)).build();
        applicationComponent3.inject(this);

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
