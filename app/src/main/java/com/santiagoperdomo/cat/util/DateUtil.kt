package com.santiagoperdomo.cat.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object{

        val FORMAT_ISO = "yyyy-MM-dd HH:MM:SS"

        fun getDateNow(format: String): String {
            return SimpleDateFormat(format).format(Date())
        }

    }

}