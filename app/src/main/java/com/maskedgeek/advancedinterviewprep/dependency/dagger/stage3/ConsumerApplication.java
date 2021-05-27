package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3;

import android.app.Application;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyDatabaseService;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyNetworkService;
import com.maskedgeek.advancedinterviewprep.dependency.stage2.DaggerApplication;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.ui.ApplicationComponent2;

import javax.inject.Inject;

public class ConsumerApplication extends Application {

    @Inject // Tells compiler to look for class constructor first to create instance or Module class to create instances and provide through Component classes
    public DependencyNetworkService mNetworkService;

    @Inject // Tells compiler to look for Module class to create instances and provide through Component classes
    public DependencyDatabaseService mDatabaseService;

    // Storing component as it needs to be passed as dependent in activitycomponent
    public ApplicationComponent mApplicationComponent;



    public void onCreate(){
            super.onCreate();

            mApplicationComponent = DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();

            mApplicationComponent.inject(this);
    }
}
