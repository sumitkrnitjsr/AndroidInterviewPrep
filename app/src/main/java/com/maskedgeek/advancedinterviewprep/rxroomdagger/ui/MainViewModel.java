package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.DatabaseService;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Address;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@ActivityScope
public class MainViewModel {

    DatabaseService databaseService;

    MutableLiveData<List<Users>> mUsers = new MutableLiveData<>();
    List<Users> mAllUsers = new ArrayList<>();
    MutableLiveData<List<Address>> mAddresses = new MutableLiveData<>();
    List<Address> mAllAddress = new ArrayList<>();

    CompositeDisposable compositeDisposable;

    @Inject
    public MainViewModel(DatabaseService databaseService, CompositeDisposable compositeDisposable) {
        this.databaseService = databaseService;
        this.compositeDisposable = compositeDisposable;

        this.compositeDisposable.add(databaseService.userDao().count()
                    .flatMap(new Function<Integer, Single<List<Long>>>(){

                        @Override
                        public Single<List<Long>> apply(Integer integer) {

                            if (integer == 0) {
                                List<Address> addresses = new ArrayList<>();
                                addresses.add(new Address("city1", "country1", 1));
                                addresses.add(new Address("city2", "country2", 2));
                                addresses.add(new Address("city3", "country3", 3));
                                addresses.add(new Address("city4", "country4", 4));
                                Log.d("rxdaggerroomactivity", "inserting addresses");
                                return databaseService.addressDAO().insertMany(addresses)
                                        .flatMap(new Function<List<Long>, Single<List<Long>>>() {
                                            @Override
                                            public Single<List<Long>> apply(List<Long> addresses) throws Throwable {
                                                List<Users> users = new ArrayList<>();
                                                users.add(new Users("Second", addresses.get(1), new Date(1622132952l)));
                                                users.add(new Users("First", addresses.get(0), new Date(1622132952l)));
                                                users.add(new Users("Third", addresses.get(2), new Date(1622132952l)));
                                                users.add(new Users("Fourth", addresses.get(3), new Date(1622132952l)));
                                                Log.d("rxdaggerroomactivity", "inserting users");
                                                return databaseService.userDao().insertMany(users);
                                            }
                                        });
                            } else {
                                    Single<List<Long>> empty = Single.just(new ArrayList<Long>(0));
                                    return empty;
                            }
                        }

                    })
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new DisposableSingleObserver<List<Long>>() {
                        @Override
                        public void onSuccess(@NonNull List<Long> longs) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    })
            );
        Log.d("rxdaggerroomactivity", "finished inserting ");
    }



    void deleteUser() {
        if(!mUsers.getValue().isEmpty()) {

            (databaseService.userDao().delete(mUsers.getValue().get(0))
                    .flatMap(new Function<Integer, Single<List<Users>>>() {
                        @Override
                        public Single<List<Users>> apply(Integer integer) throws Throwable {
                            return databaseService.userDao().getAllUsers();
                        }
                    })
                    .subscribeOn(Schedulers.io()))
                    .subscribe(new SingleObserver<List<Users>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull List<Users> users) {
                            mUsers.postValue(users);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        }
    }

    void onDestroy() {
        compositeDisposable.dispose();
    }

    void getAllUsers() {
        compositeDisposable.add(
                databaseService.userDao()
                        .getAllUsers()
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableSingleObserver<List<Users>>() {
                            @Override
                            public void onSuccess(@NonNull List<Users> users) {
                                mAllUsers = users;
                                mUsers.postValue(users);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        })

        );
    }

    void getAllAddresses() {
        compositeDisposable.add(
                databaseService.addressDAO()
                        .getAllAddresses()
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableSingleObserver<List<Address>>() {
                            @Override
                            public void onSuccess(@NonNull List<Address> addresses) {
                                mAllAddress = addresses;
                                mAddresses.postValue(addresses);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        })

        );
    }

    void deleteAddress() {
        if(mAddresses != null && !mAddresses.getValue().isEmpty()) {

            (databaseService.addressDAO().delete(mAddresses.getValue().get(0))
                    .flatMap(new Function<Integer, Single<List<Address>>>() {
                        @Override
                        public Single<List<Address>> apply(Integer integer) throws Throwable {
                            return databaseService.addressDAO().getAllAddresses();
                        }
                    })
                    .subscribeOn(Schedulers.io()))
                    .subscribe(new SingleObserver<List<Address>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull List<Address> addressses) {
                            mAllAddress = addressses;
                            mAddresses.postValue(addressses);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        }
    }
}
