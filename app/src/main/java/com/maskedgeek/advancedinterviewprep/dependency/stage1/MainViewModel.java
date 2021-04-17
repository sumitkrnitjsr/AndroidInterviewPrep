package com.maskedgeek.advancedinterviewprep.dependency.stage1;

import android.content.Context;

public class MainViewModel {

    private DatabaseService mDatabaseService;

    public MainViewModel(DatabaseService databaseService){
        // Naive Initialization
        mDatabaseService = databaseService;
    }
}
