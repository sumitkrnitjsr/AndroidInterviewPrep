package com.maskedgeek.advancedinterviewprep.rx;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.maskedgeek.androidinterviewprep.R;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RxSearchActivity extends AppCompatActivity {

    EditText editTextSearch;
    TextView textViewSuggestions;
    Observable searchText;
    Toast mToast;
    HashSet<String> dummyDB = new HashSet<>();

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_rxsearch);
        editTextSearch = findViewById(R.id.edittxt_search);
        textViewSuggestions = findViewById(R.id.txtvw_suggestions);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                getAssetList();
            }
        });
        t.start();

        fromView().debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<List<String>>() {

                    @Override
                    public boolean test(List<String> s) throws Throwable {
                        return !s.isEmpty() && s.size() > 0;
                    }
                }).distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<String> array) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(System.getProperty("line.separator"));
                            for(String str: array) {
                                stringBuilder.append(str);
                                stringBuilder.append(System.getProperty("line.separator"));
                            }

                            Log.d("RXsearchActivity", stringBuilder.toString());
                            textViewSuggestions.setText(stringBuilder.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getAssetList() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                        new InputStreamReader(getAssets().open("words_alpha.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                dummyDB.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }



    public Observable<List<String>> fromView() {

        final PublishSubject<List<String>> subject = PublishSubject.create();

        editTextSearch.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<String> result = new ArrayList<>();
                int count = 0;
                for(String str:dummyDB) {
                    if(str.contains(editable.toString())){
                        result.add(str);
                        count++;
                        if(count > 12) break;
                    }

                }
                subject.onNext(result);
                mToast.makeText(getApplicationContext(), "showing results..", Toast.LENGTH_SHORT).show();
            }

        });

        return subject;
    }


}
