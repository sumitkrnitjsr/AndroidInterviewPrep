package com.maskedgeek.advancedinterviewprep.dependency.stage0;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Activity extends AppCompatActivity {

    public MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        // Naive Initialization
        mMainViewModel = new MainViewModel(getApplicationContext());
    }
}
