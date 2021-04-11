package com.maskedgeek.androidinterviewprep.memoryleak;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.util.Log;

import com.maskedgeek.androidinterviewprep.MainApplication;
import com.maskedgeek.androidinterviewprep.base.BaseActivity;

import leakcanary.AppWatcher;

public class MemoryLeakActivity extends BaseActivity {


    @Override
    public void onStart(){
        super.onStart();
        ReferenceHoldingClass memoryLeakingObject = new ReferenceHoldingClass((this));
        Log.d(TAG, " Leak Thread started from " + Thread.currentThread().getId());
        for(int i = 0;i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                private ReferenceHoldingClass leak;

                @Override
                public void run() {
                    Log.d(TAG, " Leak Thread started at " + Thread.currentThread().getId());
                    leak = memoryLeakingObject;
                    try {
                        MainApplication mainApplication = (MainApplication) getApplicationContext();
                        Thread.sleep(60 * 1000);
                    } catch (Exception ex) {
                    }
                }
            });
            thread.start();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    class ReferenceHoldingClass {
        private Context context;
        public ReferenceHoldingClass(Context context){
            this.context = context;
        }
    }
}
