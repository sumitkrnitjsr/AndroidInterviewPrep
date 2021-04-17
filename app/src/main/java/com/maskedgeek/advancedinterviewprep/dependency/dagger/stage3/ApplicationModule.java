package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3;

import android.content.Context;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyDatabaseService;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyNetworkService;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.qualifiers.DatabaseVerQualifier;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.qualifiers.NetworkAPIQualifier;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module // Look for component class to pass the objects to required consumers
public class ApplicationModule {

    private ConsumerApplication mConsumerApplication;

    public ApplicationModule(ConsumerApplication application){
        mConsumerApplication = application;
    }


    @Singleton
    @Provides
    DependencyNetworkService createNetworkService(){
        return new DependencyNetworkService(mConsumerApplication, "abc");
    }

    @Singleton
    @Provides
    DependencyDatabaseService createDatabaseService(){
        return new DependencyDatabaseService(mConsumerApplication, "xyz", 1);
    }

    @NetworkAPIQualifier
    String createNetworkAPI(){
        return "abc";
    }

    @DatabaseVerQualifier
    String createDatabaseVersion(){
        return "xyz";
    }

    int createDatabaseID(){
        return 1;
    }

    @Provides
    Context createContext(){
        return mConsumerApplication;
    }

}
