package com.maskedgeek.androidinterviewprep.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.maskedgeek.androidinterviewprep.MainActivity;
import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.broadcastreceiver.ActivityBroadcastReceiver;
import com.maskedgeek.androidinterviewprep.customview.CustomViewActivity;
import com.maskedgeek.androidinterviewprep.dialogtoast.DialogToastActivity;
import com.maskedgeek.androidinterviewprep.fragment.MainFragmentActivity;
import com.maskedgeek.androidinterviewprep.fragment.backstack.FragmentBackStackActivity;
import com.maskedgeek.androidinterviewprep.pageradapters.ActivityPagerAdapter;
import com.maskedgeek.androidinterviewprep.recyclerview.RecyclerViewActivity;
import com.maskedgeek.androidinterviewprep.touchevent.TouchEventActivity;

public class ServiceActivity extends AppCompatActivity {

    private String TAG = ServiceActivity.class.getSimpleName();
    private BackgroundService service;
    private Boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate ");
        Log.d(TAG, " onCreate on Thread = " + Thread.currentThread().getId());
        // call finish() here to skip onPause() and onStop() and directly call onDestroy()
        setContentView(R.layout.activity_service);
        findViewById(R.id.backgroundService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BackgroundService.class);
                startService(intent);
            }
        });

        findViewById(R.id.boundService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), BackgroundService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });
        findViewById(R.id.unboundService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mBound){
                    return;
                }
                unbindService(connection);
                mBound = false;
            }
        });
        findViewById(R.id.callService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mBound){
                    return;
                }
                service.showToast();
            }
        });
    }

    @Override
    protected void onStart() {
        // activity becomes visible
        // Good place to check Permissions, LoginStatus, Network UI Update data
        super.onStart();
        Log.d(TAG, " onStart ");
        if(mBound){
            return;
        }
        Intent intent = new Intent(this, BackgroundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // called only when there is a saved instance that is previously saved by using onSaveInstanceState()
        Log.d(TAG, " onRestoreInstanceState ");
    }

    @Override
    protected void onResume() {
        // activity starts being interactive
        super.onResume();
        Log.d(TAG, " onResume ");
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        // method is used to store data before pausing the activity.
        Log.d(TAG, " onSavedInstanceState ");
        super.onSaveInstanceState(state);
    }

    @Override
    protected void onPause() {
        // activity is partially visible and stops interaction
        super.onPause();
        Log.d(TAG, " onPause ");

        // Next activity starts their onCreate here, the system is not in a hurry to stop this activity
    }

    @Override
    protected void onStop() {
        // Activity not visible
        super.onStop();
        if(!mBound){
            return;
        }
        unbindService(connection);
        mBound = false;
        Log.d(TAG, " onStop ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, " onRestart ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy ");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, " onBackPressed ");
        super.onBackPressed();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, " onServiceConnected ");
            RemoteBinder remoteBinder = (RemoteBinder) iBinder;
            service = remoteBinder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, " onServiceDisconnected ");
            mBound = false;
        }
    };

}

