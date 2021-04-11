package com.maskedgeek.androidinterviewprep.threads;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.base.BaseActivity;

public class ThreadLimit extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_thread);
        findViewById(R.id.buttonSpawnThreads).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Thread thread = new Thread() {
                    public void run() {
                        Log.d(TAG, " Parent Thread started = " + this.getId());
                        for (long i = 0; i < 1000000000; i++) {
                            Thread thread = new Thread() {
                                public void run() {
                                    Log.d(TAG, " Thread started = " + this.getId());
                                    while (true) {
                                    }
                                }
                            };
                            try {
                                thread.start();
                            } catch (Exception ex) {
                                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                };
                try {
                    thread.start();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
