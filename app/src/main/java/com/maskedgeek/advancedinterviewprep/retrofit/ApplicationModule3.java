package com.maskedgeek.advancedinterviewprep.retrofit;

import android.content.Context;

import androidx.room.Room;

import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.NetworkService2;
import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.Networking;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.DatabaseService;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.ui.Migration_1_2;
import com.maskedgeek.androidinterviewprep.BuildConfig;
import com.maskedgeek.androidinterviewprep.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Module
public class ApplicationModule3 {

    MainApplication context ;

    public ApplicationModule3(MainApplication ctx) {
            context = ctx;
    }

    @Provides
    @ApplicationContext
    Context getContext() {
        return context;
    }

    @Singleton
    @Provides
    NetworkService2 provideNetworkService2() {
        return Networking.create(
                BuildConfig.API_KEY,
                BuildConfig.BASE_URL,
                context.getCacheDir(),
                10 * 1024 * 1024l
        );
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
