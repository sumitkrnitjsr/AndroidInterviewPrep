package com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.annotations.DummyClass;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.annotations.Status;
import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.dependency.DependencyMainViewModel;

import javax.inject.Inject;

public class ConsumerActivity extends AppCompatActivity {

    private String TAG = ConsumerActivity.class.getSimpleName();

    @Inject
    public DependencyMainViewModel mMainViewModel;

    protected void onCreate(Bundle savedInstance){

        super.onCreate(savedInstance);

        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((ConsumerApplication)getApplication()).mApplicationComponent)
                .build()
                .inject(this);

        Log.d(TAG, mMainViewModel.getDummyData());

        try {
            Status status = DummyClass.class.getMethod("dummy").getAnnotation(Status.class);
            Log.d(TAG, "completion value = " +  status.completion());
        }catch(NoSuchMethodException ex){
            ex.printStackTrace();
            Log.d(TAG, "completion value caught exception = " +  ex.getMessage());
        }

    }
}
