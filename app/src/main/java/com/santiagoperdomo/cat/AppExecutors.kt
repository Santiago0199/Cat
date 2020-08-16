package com.santiagoperdomo.cat

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExecutors(diskIO: Executor, networkIO: Executor, mainThread: Executor) {

    val diskIO: Executor
    val networkIO: Executor
    val mainThread: Executor

    init {
        this.diskIO = diskIO
        this.networkIO = networkIO
        this.mainThread = mainThread
    }

    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), MainThreadExecutor()
    )

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.myLooper()!!)

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }


}