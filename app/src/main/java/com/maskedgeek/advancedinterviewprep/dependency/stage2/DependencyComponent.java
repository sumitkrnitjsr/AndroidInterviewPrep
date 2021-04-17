package com.maskedgeek.advancedinterviewprep.dependency.stage2;

public class DependencyComponent {

    public static void inject(DaggerApplication daggerApplication){
        daggerApplication.mDatabaseService = new DatabaseService(daggerApplication, "db_name", 1);
    }

    public static void inject(Activity mainActivity){
        DaggerApplication app = (DaggerApplication) mainActivity.getApplication();
        mainActivity.mMainViewModel = new MainViewModel(app.mDatabaseService);
    }
}
