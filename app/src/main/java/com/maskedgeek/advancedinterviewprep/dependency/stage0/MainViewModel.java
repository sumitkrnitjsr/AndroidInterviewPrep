package com.maskedgeek.advancedinterviewprep.dependency.stage0;

import android.content.Context;

public class MainViewModel {

    private DatabaseService mDatabaseService;
    public Context context;

    public MainViewModel(Context context){
        // Naive Initialization
        this.context = context;
        mDatabaseService = new DatabaseService(context);
    }
}
