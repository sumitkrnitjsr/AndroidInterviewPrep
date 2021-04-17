package com.maskedgeek.advancedinterviewprep.dependency.stage2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Activity extends AppCompatActivity {

    public MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        DependencyComponent.inject(this);
    }
}
