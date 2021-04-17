package com.maskedgeek.advancedinterviewprep.dependency.stage1;

import android.content.Context;

public class DatabaseService {

    private String mDBName;
    private int mVersion;
    private Context context;

    public DatabaseService(Context context, String dbname, int version){
        this.context = context;
        // Constructor Injection
        mDBName = dbname;
        mVersion = version;
    }
}
