package com.maskedgeek.advancedinterviewprep.retrofit;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule3 {

    Activity activity;

    public ActivityModule3(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    public Context provideContext() {
            return activity;
    }
}
