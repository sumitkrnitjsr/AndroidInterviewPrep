package com.maskedgeek.advancedinterviewprep.rx;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;
import io.reactivex.rxjava3.functions.Function;
import com.maskedgeek.advancedinterviewprep.dependency.stage2.Activity;
import com.maskedgeek.androidinterviewprep.base.BaseActivity;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BasicRxActivity extends BaseActivity {

    private Toast mToast;
    private String TAG = BasicRxActivity.class.getSimpleName();
    private String dummyURL = "https://raw.githubusercontent.com/sumitkrnitjsr/dummyAPI/main/employees.json";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        Observable.just("First", "Second", "Third")
                .subscribe(new Observer<String>(){

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mToast = Toast.makeText(getApplicationContext(), " onSubscribe " + Thread.currentThread(), Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id subscribed");
                        mToast.show();
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        mToast = Toast.makeText(getApplicationContext(), " onNext " + Thread.currentThread() + " " + s, Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id onNext");
                        mToast.show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mToast = Toast.makeText(getApplicationContext(), " onComplete " + Thread.currentThread(), Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id onComplete");
                        mToast.show();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getEmployeeData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EmployeeData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mToast = Toast.makeText(getApplicationContext(), " onSubscribe receiving employee data " + Thread.currentThread(), Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id onSubscribe");
                        mToast.show();
                    }

                    @Override
                    public void onNext(@NonNull EmployeeData employeeData) {
                        String data = employeeData.id + " " + employeeData.employee_name + " "
                                +employeeData.employee_age + " " + employeeData.employee_salary + " "
                                + employeeData.profile_image;
                        mToast = Toast.makeText(getApplicationContext(), " onNext  " + Thread.currentThread() + "   " + data, Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id onComplete");
                        mToast.show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mToast = Toast.makeText(getApplicationContext(), " onError " + Thread.currentThread(), Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id onError" + e.getMessage());
                        e.printStackTrace();
                        mToast.show();
                    }

                    @Override
                    public void onComplete() {
                        mToast = Toast.makeText(getApplicationContext(), " onComplete " + Thread.currentThread(), Toast.LENGTH_SHORT);
                        Log.d(TAG, Thread.currentThread() + " thread-id onComplete");
                        mToast.show();
                    }
                });

        CountDownTimer countDownTimer = new CountDownTimer(4000,4000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                    getEmployeeData().map(new Function<EmployeeData, Employee>() {

                        @Override
                        public Employee apply(EmployeeData employeeData) throws Throwable {
                            Employee employee = new Employee(employeeData.id,
                                    employeeData.employee_name, Integer.parseInt(employeeData.employee_salary),
                                    Integer.parseInt(employeeData.employee_age));
                            return employee;
                        }
                    }).filter(new Predicate<Employee>(){

                        @Override
                        public boolean test(Employee employee) throws Throwable {
                            return employee.employee_age >= 30;
                        }
                    })
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Employee>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                }

                                @Override
                                public void onNext(@NonNull Employee employeeData) {

                                    String data = employeeData.id + " " + employeeData.employee_name + " "
                                            +employeeData.employee_age + " " + employeeData.employee_salary + " ";
                                    Log.d(TAG, data + " " + (employeeData.employee_age instanceof Integer) + " "
                                            + (employeeData.employee_salary instanceof Integer));
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d(TAG, Thread.currentThread() + " thread-id onError" + e.getMessage());
                                    e.printStackTrace();
                                    mToast.show();
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
            }
        };
        countDownTimer.start();
    }
    /*
    {
"status": "success",
"data": [
	{
	"id": "1",
	"employee_name": "Tiger Nixon",
	"employee_salary": "320800",
	"employee_age": "61",
	"profile_image": ""
	},
	....
	]
}
     */

    public Observable<EmployeeData> getEmployeeData() {
        return Observable.create(new ObservableOnSubscribe<EmployeeData>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<EmployeeData> emitter) throws Throwable {
                // try {
                    URL url = new URL(dummyURL);
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                    showToast(" onSubscribe start reading " + Thread.currentThread());
                    List<EmployeeData> list = new ArrayList<>();
                    reader.beginObject();
                    while(reader.hasNext()) {
                        String nameP = reader.nextName();
                        Log.d(TAG, nameP + " nameP");
                        if(!nameP.equals("data")) {
                            String value = reader.nextString();
                            continue;
                        }
                        reader.beginArray();
                        while (reader.hasNext()) {
                            reader.beginObject();
                            String id = "", employee_name = "", employee_salary = "", employee_age = "", profile_image = "";
                            while (reader.hasNext()) {
                                String name = reader.nextName();
                                switch (name) {
                                    case "id":
                                        id = reader.nextString();
                                        break;
                                    case "employee_name":
                                        id = reader.nextString();
                                        break;
                                    case "employee_salary":
                                        employee_salary = reader.nextString();
                                        break;
                                    case "employee_age":
                                        employee_age = reader.nextString();
                                        break;
                                    case "profile_image":
                                        profile_image = reader.nextString();
                                        break;
                                }
                            }
                            reader.endObject();
                            EmployeeData emp = new EmployeeData(id, employee_name, employee_salary, employee_age, profile_image);
                            list.add(emp);
                            emitter.onNext(emp);
                        }
                        reader.endArray();
                        emitter.onComplete();
                    }

               /* } catch (Exception ex) {
                    emitter.onError(ex);
                    Log.d(TAG, ex.toString());
                    ex.printStackTrace();
                }
                */
            }
        });
    }

    public void showToast(String message){
        runOnUiThread(() -> mToast.makeText(BasicRxActivity.this, message, Toast.LENGTH_SHORT).show());
    }
}
