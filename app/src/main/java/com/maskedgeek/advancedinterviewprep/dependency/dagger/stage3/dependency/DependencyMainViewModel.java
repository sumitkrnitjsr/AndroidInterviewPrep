package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency;

import javax.inject.Inject;

public class DependencyMainViewModel {

    public DependencyNetworkService mNetworkService;
    public DependencyDatabaseService mDatabaseService;

    @Inject
    public DependencyMainViewModel(DependencyDatabaseService dbService, DependencyNetworkService nwService){
        mNetworkService = nwService;
        mDatabaseService = dbService;
    }

    public String getDummyData(){
        return mNetworkService.mAPI + " " + mDatabaseService.mDBName + " " + mDatabaseService.mVersion;
    }

}
