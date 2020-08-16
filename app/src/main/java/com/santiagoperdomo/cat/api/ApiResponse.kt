package com.santiagoperdomo.cat.api

import android.util.Log
import retrofit2.Response
import java.io.IOException

class ApiResponse<T: Any>() {

    var code: Int = 0
    var body: T? = null
    var errorMessage: String? = null

    constructor(error: Throwable) : this() {
        code = 500
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) : this() {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Log.d(ignored.message, "Error while parsing response")
                }
            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
    }

    fun isSuccessful(): Boolean {
        return code >= 200 && code < 300
    }

}