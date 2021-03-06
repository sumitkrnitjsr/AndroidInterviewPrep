package com.maskedgeek.androidinterviewprep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.maskedgeek.advancedinterviewprep.AdvancedAndroidActivity;
import com.maskedgeek.androidinterviewprep.aidl.RemoteService;
import com.maskedgeek.androidinterviewprep.broadcastreceiver.ActivityBroadcastReceiver;
import com.maskedgeek.androidinterviewprep.customview.CustomViewActivity;
import com.maskedgeek.androidinterviewprep.dialogtoast.DialogToastActivity;
import com.maskedgeek.androidinterviewprep.fragment.MainFragmentActivity;
import com.maskedgeek.androidinterviewprep.fragment.backstack.FragmentBackStackActivity;
import com.maskedgeek.androidinterviewprep.memoryleak.MemoryLeakActivity;
import com.maskedgeek.androidinterviewprep.pageradapters.ActivityPagerAdapter;
import com.maskedgeek.androidinterviewprep.recyclerview.RecyclerViewActivity;
import com.maskedgeek.androidinterviewprep.services.BackgroundService;
import com.maskedgeek.androidinterviewprep.services.ServiceActivity;
import com.maskedgeek.androidinterviewprep.sharedpreference.EncryptedSharedPrefActivity;
import com.maskedgeek.androidinterviewprep.sharedpreference.SharedPrefActivity;
import com.maskedgeek.androidinterviewprep.threads.ThreadLimit;
import com.maskedgeek.androidinterviewprep.touchevent.TouchEventActivity;

/* Extend Activity class and define in Manifest file
<action
                android:name="android.intent.action.MAIN" />
<category
                android:name="android.intent.category.LAUNCHER" />

*/

// Empty default Layout visible


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate ");
        Log.d(TAG,  " onCreate on Thread = " + Thread.currentThread().getId());
        // call finish() here to skip onPause() and onStop() and directly call onDestroy()
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonFragments).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MainFragmentActivity.class));
            }
        });
        findViewById(R.id.buttonAdapter).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ActivityPagerAdapter.class));
            }
        });
        findViewById(R.id.buttonBackStack).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, FragmentBackStackActivity.class));
            }
        });
        findViewById(R.id.buttonCustomView).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
            }
        });
        findViewById(R.id.buttonTouchEvent).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
            }
        });
        findViewById(R.id.buttonrecyclerview).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });
        findViewById(R.id.buttonDialogToast).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, DialogToastActivity.class));
            }
        });
        findViewById(R.id.buttonbroadcastreceiver).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ActivityBroadcastReceiver.class));
            }
        });
        findViewById(R.id.buttonService).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
            }
        });
        findViewById(R.id.buttonThreads).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ThreadLimit.class));
            }
        });
        findViewById(R.id.buttonMemoryLeak).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MemoryLeakActivity.class));
            }
        });
        findViewById(R.id.buttonSharedPref).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SharedPrefActivity.class));
            }
        });
        findViewById(R.id.buttonEncSharedPref).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, EncryptedSharedPrefActivity.class));
            }
        });

        findViewById(R.id.advanced_android).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, AdvancedAndroidActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart(){
        // activity becomes visible
        // Good place to check Permissions, LoginStatus, Network UI Update data
        super.onStart();
        Log.d(TAG, " onStart ");
        Intent intent = new Intent(this, RemoteService.class);
        startService(intent);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // called only when there is a saved instance that is previously saved by using onSaveInstanceState()
        Log.d(TAG, " onRestoreInstanceState ");
    }

    @Override
    protected void onResume(){
        // activity starts being interactive
        super.onResume();
        Log.d(TAG, " onResume ");
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        // method is used to store data before pausing the activity.
        Log.d(TAG, " onSavedInstanceState ");
        super.onSaveInstanceState(state);
    }

    @Override
    protected void onPause(){
        // activity is partially visible and stops interaction
        super.onPause();
        Log.d(TAG, " onPause ");

        // Next activity starts their onCreate here, the system is not in a hurry to stop this activity
    }

    @Override
    protected void onStop() {
        // Activity not visible
        super.onStop();
        Log.d(TAG, " onStop ");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, " onRestart ");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, " onDestroy ");
    }

    @Override
    public void onBackPressed(){
        Log.d(TAG, " onBackPressed ");
        super.onBackPressed();
    }
}
