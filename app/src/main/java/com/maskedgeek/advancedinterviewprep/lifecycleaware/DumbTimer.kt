package com.maskedgeek.advancedinterviewprep.lifecycleaware

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast

class DumbTimer(context: Context) {


    var isPause: Boolean = true

    private var timer = object :  CountDownTimer(60000, 3000) {
        override fun onFinish() {
            if(!isPause)
            Toast.makeText(context, " Finished on ${Thread.currentThread().id}" , Toast.LENGTH_SHORT).show()
        }

        override fun onTick(p0: Long) {
            if(!isPause)
            Toast.makeText(context, "${p0} on ${Thread.currentThread().id}", Toast.LENGTH_SHORT).show()
        }
    }


    fun startTimer() {
        timer.start();
        unpauseTimer()
    }

    fun pauseTimer() {
        isPause = true
    }

    fun unpauseTimer() {
        isPause = false
    }

    fun stopTimer() {
        timer.cancel()
        isPause = true
    }



}