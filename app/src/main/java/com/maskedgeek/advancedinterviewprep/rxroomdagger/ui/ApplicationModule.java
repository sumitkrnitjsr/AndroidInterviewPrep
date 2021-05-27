package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import android.content.Context;

import androidx.room.Room;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.ConsumerApplication;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.DatabaseService;
import com.maskedgeek.androidinterviewprep.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    MainApplication context ;

    public ApplicationModule(MainApplication ctx) {
            context = ctx;
    }

    @Provides
    @ApplicationContext
    Context getContext() {
        return context;
    }

    @Singleton
    @Provides
    DatabaseService provideDatabaseService() {
        return Room.databaseBuilder(context, DatabaseService.class, "users-db")
                .addMigrations(new Migration_1_2(1, 2))
                .build();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
