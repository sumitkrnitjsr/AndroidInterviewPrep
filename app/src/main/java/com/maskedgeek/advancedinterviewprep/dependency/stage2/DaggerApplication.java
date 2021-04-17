package com.maskedgeek.advancedinterviewprep.dependency.stage2;

import android.app.Application;

public class DaggerApplication extends Application {

    public DatabaseService mDatabaseService;

    @Override
    public void onCreate(){
        super.onCreate();
        DependencyComponent.inject(this);
    }
}
