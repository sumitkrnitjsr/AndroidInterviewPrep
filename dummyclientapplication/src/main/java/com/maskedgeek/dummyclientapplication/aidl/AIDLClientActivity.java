package com.maskedgeek.dummyclientapplication.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.maskedgeek.dummyclientapplication.R;

import aidl.IRemoteService;

public class AIDLClientActivity extends Activity {

    private String TAG = AIDLClientActivity.class.getSimpleName();
    private IRemoteService mRemoteService;

    // To get status about service connected
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, " onServiceConnected on " + Thread.currentThread().getId() + " in Process " + Process.myPid());
            mRemoteService = IRemoteService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, " onServiceDisconnected on " + Thread.currentThread().getId() + " in Process " + Process.myPid());
            mRemoteService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate ");
        Log.d(TAG,  " onCreate on Thread = " + Thread.currentThread().getId());
        // call finish() here to skip onPause() and onStop() and directly call onDestroy()
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonaidl).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                    if(mRemoteService != null){
                        try {
                            int pid = mRemoteService.getPid();
                            Toast.makeText(getApplicationContext(), " RemoteService in Process Pid = " + pid + " at in Process Pid = " + Process.myPid(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG , " RemoteService in Process Pid = " + pid + " at in Process Pid = " + Process.myPid());
                        }catch(Exception ex){
                            Toast.makeText(getApplicationContext(), " RemoteService Exception", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), " AIDL service not connected ", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    @Override
    protected void onStart(){
        // activity becomes visible
        // Good place to check Permissions, LoginStatus, Network UI Update data
        super.onStart();
        Log.d(TAG, " onStart ");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.maskedgeek.androidinterviewprep", "com.maskedgeek.androidinterviewprep.aidl.RemoteService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
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
        unbindService(serviceConnection);
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
