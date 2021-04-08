package com.maskedgeek.androidinterviewprep.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.maskedgeek.androidinterviewprep.MainActivity;
import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.customview.CustomViewActivity;
import com.maskedgeek.androidinterviewprep.fragment.MainFragmentActivity;
import com.maskedgeek.androidinterviewprep.fragment.backstack.FragmentBackStackActivity;
import com.maskedgeek.androidinterviewprep.pageradapters.ActivityPagerAdapter;
import com.maskedgeek.androidinterviewprep.touchevent.TouchEventActivity;

import java.util.ArrayList;

public class RecyclerViewActivity  extends AppCompatActivity implements AdapterActivityCommunicationInterface{

    private String TAG = RecyclerViewActivity.class.getSimpleName();
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate ");
        // call finish() here to skip onPause() and onStop() and directly call onDestroy()
        setContentView(R.layout.activity_recyclerview);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        for(int i = 0;i < 100;i++){
            String var = Integer.toString(i) + Integer.toString(i + 1) + Integer.toString(i + 2);
            list.add(var);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new RecyclerViewAdapter(list, this));
    }

    @Override
    protected void onStart(){
        // activity becomes visible
        // Good place to check Permissions, LoginStatus, Network UI Update data
        super.onStart();
        Log.d(TAG, " onStart ");
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

    @Override
    public void onClick(int position) {
        Toast.makeText(this, list.get(position) + " clicked", Toast.LENGTH_SHORT).show();
    }
}
