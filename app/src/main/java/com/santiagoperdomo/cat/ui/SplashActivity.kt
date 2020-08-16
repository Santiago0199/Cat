package com.santiagoperdomo.cat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var bindingSplashActivity: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState?: Bundle())
        bindingSplashActivity = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler().postDelayed({
            val i = Intent(this@SplashActivity, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }

}