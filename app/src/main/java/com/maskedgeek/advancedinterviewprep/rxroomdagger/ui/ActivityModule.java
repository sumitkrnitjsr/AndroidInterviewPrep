package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ActivityContext
    @Provides
    public Context provideContext() {
            return activity;
    }
}
