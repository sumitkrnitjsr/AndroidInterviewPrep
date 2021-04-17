package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency;

import android.content.Context;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.ConsumerApplication;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.qualifiers.DatabaseVerQualifier;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DependencyDatabaseService {

    public String mDBName;
    public int mVersion;
    private ConsumerApplication context;

    @Inject
    public DependencyDatabaseService(ConsumerApplication context, @DatabaseVerQualifier String dbname, int version){
        this.context = context;
        // Constructor Injection
        mDBName = dbname;
        mVersion = version;
    }
}
