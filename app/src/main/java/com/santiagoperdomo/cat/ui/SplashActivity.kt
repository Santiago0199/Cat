package com.santiagoperdomo.cat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.databinding.ActivitySplashBinding
import com.santiagoperdomo.cat.util.Constants
import com.santiagoperdomo.cat.util.SharedPreferencesManager
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var bindingSplashActivity: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState?: Bundle())
        bindingSplashActivity = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        SharedPreferencesManager.setSomeStringValue(this, Constants.SUB_ID, generateSubId())

        Handler().postDelayed({
            val i = Intent(this@SplashActivity, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }

    fun generateSubId(): String {
        val ran = Random()
        val top = 3
        var data = 0
        var dat = ""
        for (i in 0..top) {
            data = (ran.nextInt(25) + 97)
            dat = data.toString() + dat
        }
        println(dat)
        return dat
    }

}