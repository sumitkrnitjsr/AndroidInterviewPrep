package com.maskedgeek.advancedinterviewprep

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.maskedgeek.advancedinterviewprep.lifecycleaware.DumbLifecycleActivity
import com.maskedgeek.advancedinterviewprep.lifecycleaware.LifecycleAwareActivity
import com.maskedgeek.advancedinterviewprep.lifecycleaware.LifecycleAwareTimer
import com.maskedgeek.advancedinterviewprep.lifecycleaware.LiveDataActivity
import com.maskedgeek.advancedinterviewprep.rx.BasicRxActivity
import com.maskedgeek.advancedinterviewprep.rx.RxSearchActivity
import com.maskedgeek.androidinterviewprep.R
import com.maskedgeek.androidinterviewprep.base.BaseActivity


class AdvancedAndroidActivity : BaseActivity() {
    private val TAG = AdvancedAndroidActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        // Same as constructor
        // inject dependencies
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced)
        findViewById<View>(R.id.buttonDumbLifecycle).setOnClickListener {
            startActivity(Intent(this@AdvancedAndroidActivity, DumbLifecycleActivity::class.java))
        }
        findViewById<View>(R.id.buttonLifecycleAware).setOnClickListener {
            startActivity(Intent(this@AdvancedAndroidActivity, LifecycleAwareActivity::class.java))
        }
        findViewById<View>(R.id.buttonLiveData).setOnClickListener {
            startActivity(Intent(this@AdvancedAndroidActivity, LiveDataActivity::class.java))
        }
        findViewById<View>(R.id.buttonRx).setOnClickListener {
            startActivity(Intent(this@AdvancedAndroidActivity, BasicRxActivity::class.java))
        }
        findViewById<View>(R.id.buttonRxSearch).setOnClickListener {
            startActivity(Intent(this@AdvancedAndroidActivity, RxSearchActivity::class.java))
        }

    }

    override fun onStart() {
        // activity becomes visible
        // Good place to check Permissions, LoginStatus, Network UI Update data
        super.onStart()
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // called only when there is a saved instance that is previously saved by using onSaveInstanceState()
    }

    override fun onResume() {
        // activity starts being interactive
        super.onResume()
    }

    public override fun onSaveInstanceState(state: Bundle) {
        // method is used to store data before pausing the activity.
        super.onSaveInstanceState(state)
    }

    override fun onPause() {
        // activity is partially visible and stops interaction
        super.onPause()
        // Next activity starts their onCreate here, the system is not in a hurry to stop this activity
    }

    override fun onStop() {
        // Activity not visible
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}