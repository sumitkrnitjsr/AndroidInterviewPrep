package com.maskedgeek.advancedinterviewprep.lifecycleaware

import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AwareViewModel() : ViewModel() {

    val timerData = MutableLiveData<String>()

    private var timer =  object: CountDownTimer(60000, 3000) {
        override fun onFinish() {
            timerData.postValue(" Finished on ${Thread.currentThread().id}")
        }

        override fun onTick(p0: Long) {
            timerData.postValue("${p0} on ${Thread.currentThread().id}")
        }

    }

    fun onStart() {
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}