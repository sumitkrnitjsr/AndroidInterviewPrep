package com.maskedgeek.advancedinterviewprep.dependency.stage0;

import android.content.Context;

public class DatabaseService {

    private String mDBName;
    private int mVersion;
    private Context context;

    public DatabaseService(Context context){
        this.context = context;
        // Naive Initialization
        mDBName = "db_name";
        mVersion = 1;
    }
}
