package com.maskedgeek.androidinterviewprep.fragment.backstack;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.fragment.ProgrammaticFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentBackStackActivity extends AppCompatActivity implements ActivityFragmentStackCommunicationInterface {

    private String TAG = FragmentBackStackActivity.class.getSimpleName();
    private List<String> backstackTags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate ");
        // call finish() here to skip onPause() and onStop() and directly call onDestroy()
        setContentView(R.layout.activity_fragmentbackstack);
        setUpFragment(savedInstanceState);
    }

    private void setUpFragment(Bundle savedInstance) {
        if(savedInstance != null){
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.bottomFragment, ProgrammaticFragment.class, null,"first")
                .commit();
        backstackTags.add("first");
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
        if(getSupportFragmentManager().getBackStackEntryCount() < 1) {
            super.onBackPressed();
            return;
        }
        getSupportFragmentManager().popBackStackImmediate();
        if(backstackTags.size() < 1){
            return;
        }
        Log.d(TAG, "removing " +  backstackTags.get(backstackTags.size() - 1));
        backstackTags.remove(backstackTags.size() - 1);
    }

    @Override
    public String onClick(int id) {
        Random random = new Random();
        String text = "";
        for(int i = 0;i < 10;i++){
            int num = 'a' + random.nextInt()%26;
            text = text + (char)num;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id",random.nextInt());
        switch(id){
            case 1:
                communicateBetweenFragments();
                return text;
            case 2:
                return text;
            case 3 :
                String tag = Integer.toString(random.nextInt());
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.bottomFragment, ProgrammaticFragment.class,bundle,tag)
                        .addToBackStack(backstackTags.get(backstackTags.size() - 1))
                        .commit();
                backstackTags.add(tag);
                Log.d(TAG, " added " +  backstackTags.get(backstackTags.size() - 1));
        }

        return "";
    }

    private void communicateBetweenFragments(){
        ProgrammaticFragment execute = (ProgrammaticFragment) getSupportFragmentManager().findFragmentById(R.id.bottomFragment);
        execute.getDatafromSiblingFragment();
    }
}
