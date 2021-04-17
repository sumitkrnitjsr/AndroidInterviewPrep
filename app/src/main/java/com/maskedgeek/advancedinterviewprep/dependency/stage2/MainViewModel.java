package com.maskedgeek.advancedinterviewprep.dependency.stage2;

public class MainViewModel {

    private DatabaseService mDatabaseService;

    public MainViewModel(DatabaseService databaseService){
        // Naive Initialization
        mDatabaseService = databaseService;
    }
}
