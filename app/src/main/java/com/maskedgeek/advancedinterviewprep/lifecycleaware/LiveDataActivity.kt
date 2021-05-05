package com.maskedgeek.advancedinterviewprep.lifecycleaware

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maskedgeek.androidinterviewprep.R
import com.maskedgeek.androidinterviewprep.base.BaseActivity

class LiveDataActivity : BaseActivity() {

    private lateinit var viewModel: AwareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dumbtimer)
        viewModel = ViewModelProvider(this).get(AwareViewModel::class.java)
        viewModel.timerData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        })

        viewModel.onStart()
    }
}