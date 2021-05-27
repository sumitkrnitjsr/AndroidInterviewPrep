package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import android.content.Context;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.ConsumerApplication;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.DatabaseService;
import com.maskedgeek.androidinterviewprep.MainApplication;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface  ApplicationComponent2 {

    void inject(MainApplication mainApplication);

    DatabaseService getDatabaseService();

    CompositeDisposable getCompositeDisposable();

    @ApplicationContext
    Context getContext();
}
