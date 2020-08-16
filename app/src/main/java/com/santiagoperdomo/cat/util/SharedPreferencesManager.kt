package com.santiagoperdomo.cat.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {

    companion object{

        val APP_SETTINGS_FILE = "APP_SETTINGS"

        private fun getSharedPreference(context: Context): SharedPreferences{
            return context.getSharedPreferences(APP_SETTINGS_FILE, Context.MODE_PRIVATE)
        }

        fun setSomeStringValue(context: Context, key: String, value: String){
            val editor = getSharedPreference(context).edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getSomeStringValue(context: Context, key: String): String{
            return getSharedPreference(context).getString(key, "")!!
        }

    }

}