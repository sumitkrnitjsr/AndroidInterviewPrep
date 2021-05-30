package com.maskedgeek.advancedinterviewprep.retrofit;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.NetworkService2;
import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.Networking;
import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.request.DummyRequest;
import com.maskedgeek.advancedinterviewprep.retrofit.data.remote.response.DummyResponse;
import com.maskedgeek.advancedinterviewprep.retrofit.model.Dummy;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@ActivityScope3
public class MainViewModel3 {

    NetworkService2 networkService2;
    CompositeDisposable compositeDisposable;

    MutableLiveData<List<Dummy>> dummies = new MutableLiveData<>();

    @Inject
    public MainViewModel3(NetworkService2 networkService2, CompositeDisposable compositeDisposable) {
                this.networkService2 = networkService2;
                this.compositeDisposable = compositeDisposable;
    }

    void getDummies() {
        compositeDisposable.add(
                networkService2.doDummyCall(new DummyRequest("123"), Networking.API_KEY)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<DummyResponse>() {
                    @Override
                    public void onSuccess(@NonNull DummyResponse dummyResponse) {
                                    dummies.postValue(dummyResponse.data);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            Log.d("mainViewModel3 ", e.getMessage());
                    }
                })
        );
    }

    void onDestroy() {
        compositeDisposable.dispose();
    }

}
