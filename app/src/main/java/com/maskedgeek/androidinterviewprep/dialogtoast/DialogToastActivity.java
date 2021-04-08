package com.maskedgeek.androidinterviewprep.dialogtoast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.maskedgeek.androidinterviewprep.MainActivity;
import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.customview.CustomViewActivity;
import com.maskedgeek.androidinterviewprep.fragment.MainFragmentActivity;
import com.maskedgeek.androidinterviewprep.fragment.backstack.FragmentBackStackActivity;
import com.maskedgeek.androidinterviewprep.pageradapters.ActivityPagerAdapter;
import com.maskedgeek.androidinterviewprep.recyclerview.RecyclerViewActivity;
import com.maskedgeek.androidinterviewprep.touchevent.TouchEventActivity;

public class DialogToastActivity extends AppCompatActivity implements DialogActivityCommunicationListener{

    private String TAG = DialogToastActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate ");
        // call finish() here to skip onPause() and onStop() and directly call onDestroy()
        setContentView(R.layout.activity_dialogtoast);
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showDialog();
            }
        });

    }

    private void showDialog() {
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "agree");
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
    public void agree(Boolean result) {
        Toast.makeText(this, "Agree = " + Boolean.toString(result), Toast.LENGTH_SHORT).show();
    }
}
