package com.maskedgeek.advancedinterviewprep.retrofit;

import android.content.Context;

import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.NetworkService2;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.DatabaseService;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.ui.ApplicationModule;
import com.maskedgeek.androidinterviewprep.MainApplication;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
@Component(modules = {ApplicationModule3.class})
public interface ApplicationComponent3 {

    void inject(MainApplication mainApplication);

    NetworkService2 getNetworkService2();

    DatabaseService getDatabaseService();

    CompositeDisposable getCompositeDisposable();

    @ApplicationContext
    Context getContext();
}
