package com.santiagoperdomo.cat.repository

class Resource<T: Any>(status: Status, data: T?, message: String?) {

    val status: Status
    val message: String?
    val data: T?

    init {
        this.status = status
        this.data = data
        this.message = message
    }

    companion object{

        fun <T : Any> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T : Any> error(msg: String, data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T : Any> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}