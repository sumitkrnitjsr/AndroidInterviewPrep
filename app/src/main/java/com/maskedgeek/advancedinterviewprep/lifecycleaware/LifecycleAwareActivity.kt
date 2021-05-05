package com.maskedgeek.advancedinterviewprep.lifecycleaware

import android.os.Bundle
import com.maskedgeek.androidinterviewprep.R
import com.maskedgeek.androidinterviewprep.base.BaseActivity

class LifecycleAwareActivity : BaseActivity() {

    private lateinit var timer: LifecycleAwareTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dumbtimer)
        timer = LifecycleAwareTimer(this, this)
    }
}