package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.maskedgeek.advancedinterviewprep.dependency.dagger.stage3.ConsumerApplication;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Address;
import com.maskedgeek.advancedinterviewprep.rxroomdagger.data.local.db.entity.Users;
import com.maskedgeek.androidinterviewprep.MainApplication;
import com.maskedgeek.androidinterviewprep.R;

import java.util.List;

import javax.inject.Inject;

public class RxDaggerRoomActivity extends AppCompatActivity {

        @Inject
        MainViewModel mainViewModel;

        MainApplication mainApplication;

        @Override
        public void onCreate(Bundle savedInstance) {
            mainApplication = (MainApplication) getApplication();
            getDependencies();
            super.onCreate(savedInstance);
            setContentView(R.layout.activity_rxdaggerroom);
            TextView txtvw = findViewById(R.id.textview);
            TextView txtvwaddress = findViewById(R.id.textviewaddress);
            
            mainViewModel.mUsers.observe(this, new Observer<List<Users>>() {
                @Override
                public void onChanged(List<Users> users) {
                    txtvw.setText(users.toString());
                }
            });

            mainViewModel.mAddresses.observe(this, new Observer<List<Address>>() {
                @Override
                public void onChanged(List<Address> addresses) {
                    txtvwaddress.setText(addresses.toString());
                }
            });

        }

        private void getDependencies() {
                DaggerActivityComponent2.builder()
                        .applicationComponent2(mainApplication.applicationComponent)
                        .activityModule(new ActivityModule(this))
                        .build()
                        .inject(this);

        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainViewModel.onDestroy();
    }

    @Override
    public void onStop() {
            super.onStop();
            mainViewModel.deleteAddress();
    }

    @Override
    public void onStart() {
        super.onStart();
        mainViewModel.getAllAddresses();
        mainViewModel.getAllUsers();
    }
}
