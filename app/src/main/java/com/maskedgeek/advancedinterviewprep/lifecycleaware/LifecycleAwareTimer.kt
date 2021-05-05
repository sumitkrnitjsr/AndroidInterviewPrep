package com.maskedgeek.advancedinterviewprep.lifecycleaware

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class LifecycleAwareTimer (context: Context, lifecycleOwner: LifecycleOwner) : LifecycleObserver{


    private var lifecycle: Lifecycle = lifecycleOwner.lifecycle

    init {
        lifecycle.addObserver(this)
    }

    private var timer = object :  CountDownTimer(60000, 3000) {
        override fun onFinish() {
            if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                Toast.makeText(context, " Finished on ${Thread.currentThread().id}" , Toast.LENGTH_SHORT).show()
        }

        override fun onTick(p0: Long) {

                if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                Toast.makeText(context, "${p0} on ${Thread.currentThread().id}", Toast.LENGTH_SHORT).show()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun start() {
        timer.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        timer.cancel()
    }
}