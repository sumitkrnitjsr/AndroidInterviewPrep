package com.maskedgeek.androidinterviewprep.fragment.backstack;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.fragment.MainFragment;

// USED IN ACTIVITY TO ADD FRAGMENT
// getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.bottomFragment, ProgrammaticFragment.class, null)
//                .commit();

public class ProgrammaticFragmentStack extends Fragment {

    // To provide any callbacks between activity and fragment using interface
    private ActivityFragmentStackCommunicationInterface listener = null;
    private String TAG = MainFragment.class.getSimpleName();
    private TextView titletxt;
    private TextView descriptiontxt;


    public ProgrammaticFragmentStack(){
        super(R.layout.fragment_programmatic);
    }

    @Override
    public void onAttach(Context activity){
        // Receives activity object for callback mechanism to activity
        Log.d(TAG," OnAttach ");
        super.onAttach(activity);
        listener = (ActivityFragmentStackCommunicationInterface)getActivity();
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
        titletxt = (TextView)(view.findViewById(R.id.title));
        descriptiontxt = (TextView)(view.findViewById(R.id.description));
        if(savedInstance != null){
            /*
            String title = requireArguments().getString("title");
            String description = requireArguments().getString("description");
            if(title != null && description != null){
                titletxt.setText(title);
                descriptiontxt.setText(description);
            }
             */
        }
        view.findViewById(R.id.dynamic).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                titletxt.setText(listener.onClick(2));
                descriptiontxt.setText(listener.onClick(2));
            }
        });
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
