package com.santiagoperdomo.cat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var bindingSplashActivity: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState?: Bundle())
        bindingSplashActivity = DataBindingUtil.setContentView(this, R.layout.activity_splash)

    }

}