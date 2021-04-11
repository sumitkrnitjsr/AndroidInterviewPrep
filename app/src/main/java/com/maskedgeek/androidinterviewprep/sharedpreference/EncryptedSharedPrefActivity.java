package com.maskedgeek.androidinterviewprep.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.maskedgeek.androidinterviewprep.base.BaseActivity;

public class EncryptedSharedPrefActivity extends BaseActivity {

    private String mSharedPreferenceName = "login_count";
    private String mSharedPreferenceCount = "count";

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        try {
            String keys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            SharedPreferences encryptedSharedPreferences = EncryptedSharedPreferences
                    .create(mSharedPreferenceName, keys, getApplicationContext(),
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            int loginCount = encryptedSharedPreferences.getInt(mSharedPreferenceCount,0);
            Toast.makeText(this, " Login Attempt = " + loginCount, Toast.LENGTH_SHORT).show();
            encryptedSharedPreferences.edit().putInt(mSharedPreferenceCount, (loginCount + 1)).apply();
        }catch(Exception ex){

        }
    }
}
