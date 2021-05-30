package com.maskedgeek.advancedinterviewprep.retrofit;

import android.os.Bundle;
import android.widget.TextView;


import androidx.lifecycle.Observer;

import com.maskedgeek.advancedinterviewprep.retrofit.model.Dummy;
import com.maskedgeek.androidinterviewprep.MainApplication;
import com.maskedgeek.androidinterviewprep.R;
import com.maskedgeek.androidinterviewprep.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class RetrofitActivity extends BaseActivity {

    MainApplication mainApplication;

    @Inject
    MainViewModel3 mainViewModel;
    TextView dummytxt;

    @Override
    public void onCreate(Bundle savedInstance) {
        mainApplication = (MainApplication) getApplication();
        getDependencies();
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_retrofit);
        dummytxt = findViewById(R.id.response_txt);
        mainViewModel.dummies.observe(this, new Observer<List<Dummy>>() {
            @Override
            public void onChanged(List<Dummy> dummies) {
                    dummytxt.setText(dummies.toString());
            }
        });
        mainViewModel.getDummies();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mainViewModel.onDestroy();
    }

    private void getDependencies() {
            DaggerActivityComponent3.builder()
                                    .applicationComponent3(mainApplication.applicationComponent3)
                    .activityModule3(new ActivityModule3(this))
                    .build()
                    .inject(this);
    }
}
