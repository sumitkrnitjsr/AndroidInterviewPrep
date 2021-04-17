package com.maskedgeek.advancedinterviewprep.dependency.stage1;

import android.app.Application;

public class DaggerApplication extends Application {

    public DatabaseService mDatabaseService;

    @Override
    public void onCreate(){
        super.onCreate();
        // Naive Initialization
        mDatabaseService = new DatabaseService(this, "db_name", 1);
    }
}
