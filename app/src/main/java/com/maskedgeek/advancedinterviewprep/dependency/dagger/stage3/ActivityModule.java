package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyDatabaseService;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyMainViewModel;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyNetworkService;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.qualifiers.DatabaseVerQualifier;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.qualifiers.NetworkAPIQualifier;
import com.maskedgeek.advancedinterviewprep.dependency.stage0.MainViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module // Look for component class to pass the objects to required consumers
public class ActivityModule {

    private ConsumerActivity mConsumerActivity;

    public ActivityModule(ConsumerActivity activity){
        mConsumerActivity = activity;
    }

    @Provides
    DependencyMainViewModel createMainViewModel(DependencyNetworkService networkService, DependencyDatabaseService databaseService){
        return new DependencyMainViewModel(databaseService, networkService);
    }


}
