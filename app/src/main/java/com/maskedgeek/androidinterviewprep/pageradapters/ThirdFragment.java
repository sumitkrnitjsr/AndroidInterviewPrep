package com.maskedgeek.androidinterviewprep.pageradapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.maskedgeek.androidinterviewprep.R;


public class ThirdFragment extends Fragment {

    // To provide any callbacks between activity and fragment using interface
    private String TAG = ThirdFragment.class.getSimpleName();


    public ThirdFragment(){
        super(R.layout.fragment_third);
    }

    @Override
    public void onAttach(Context activity){
        // Receives activity object for callback mechanism to activity
        Log.d(TAG," OnAttach ");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstance){
        //Initialization
        //Inject Dependencies
        Log.d(TAG," OnCreate ");
        super.onCreate(savedInstance);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance){
        Log.d(TAG," onViewCreated ");
    }

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);
        Log.d(TAG," onActivityCreated ");
    }

    @Override
    public void onStart(){
        // Visible
        super.onStart();
        Log.d(TAG," onStart ");
    }

    @Override
    public void onResume(){
        // Interactive
        super.onResume();
        Log.d(TAG, " onResume ");
    }

    @Override
    public void onPause(){
        //Non Interactive
        super.onPause();
        Log.d(TAG, " onPause ");
    }

    @Override
    public void onStop(){
        //Invisible
        super.onStop();
        Log.d(TAG, " onStop ");
    }

    @Override
    public void onDestroyView(){
        //Removed from activity hierarchy or returned from layout backstack to onCreateView
        super.onDestroyView();
        Log.d(TAG, " onDestroyView ");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, " onDestroy ");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(TAG, " onDetach ");
    }


}
