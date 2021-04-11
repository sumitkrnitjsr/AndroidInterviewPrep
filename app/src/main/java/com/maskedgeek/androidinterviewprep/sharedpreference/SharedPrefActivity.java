package com.maskedgeek.androidinterviewprep.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.maskedgeek.androidinterviewprep.base.BaseActivity;

public class SharedPrefActivity extends BaseActivity {


    private String mSharedPreferenceName = "login_count";
    private String mSharedPreferenceCount = "count";

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        SharedPreferences sharedPreferences = getSharedPreferences(mSharedPreferenceName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int loginCount = sharedPreferences.getInt(mSharedPreferenceCount,0);
        Toast.makeText(this, " Login Attempt = " + loginCount, Toast.LENGTH_SHORT).show();
        editor.putInt(mSharedPreferenceCount, (loginCount + 1));
        editor.apply();
    }
}
