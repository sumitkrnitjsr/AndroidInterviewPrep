package com.maskedgeek.androidinterviewprep.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BatteryBroadcastReceiver extends BroadcastReceiver {

    private String TAG = BatteryBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent){
        if(intent == null){
            return;
        }
        if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
            Toast.makeText(context, " Battery Changed", Toast.LENGTH_SHORT).show();
            Log.d(TAG, " Battery changed ");
        }else if(intent.getAction().equals(Constants.LOCALBROADCAST)){
            Toast.makeText(context, " Local Broadcast", Toast.LENGTH_SHORT).show();
            Log.d(TAG, " Local Broadcast ");
        }else if(intent.getAction().equals(Constants.GLOBALBROADCAST)){
            Toast.makeText(context, " Global Broadcast", Toast.LENGTH_SHORT).show();
            Log.d(TAG, " Global Broadcast ");
        }
    }
}
