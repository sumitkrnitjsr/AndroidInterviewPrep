package com.maskedgeek.advancedinterviewprep.lifecycleaware

import android.os.Bundle
import com.maskedgeek.androidinterviewprep.R
import com.maskedgeek.androidinterviewprep.base.BaseActivity

class DumbLifecycleActivity : BaseActivity(){

    private lateinit var dumbTimer : DumbTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dumbtimer)
        dumbTimer = DumbTimer(this)
        dumbTimer.startTimer()
    }

    override fun onStart() {
        super.onStart()
        dumbTimer.unpauseTimer()
    }

    override fun onPause() {
        super.onPause()
        dumbTimer.pauseTimer()
    }
    override fun onDestroy() {
        super.onDestroy()
        dumbTimer.stopTimer()
    }

}
