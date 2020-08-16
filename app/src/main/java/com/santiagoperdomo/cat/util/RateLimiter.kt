package com.santiagoperdomo.cat.util

import android.os.SystemClock
import android.util.ArrayMap
import java.util.concurrent.TimeUnit

class RateLimiter<Key>(timeout: Long, timeUnit: TimeUnit) {

    private var timestamps: ArrayMap<Key, Long>
    private val timeout: Long

    init {
        timestamps = ArrayMap()
        this.timeout = timeUnit.toMillis(timeout)
    }

    @Synchronized
    fun shouldFetch(key: Key): Boolean {
        val lastFetched = timestamps[key]
        val now = now()
        if (lastFetched == null) {
            timestamps[key] = now
            return true
        }
        if (now - lastFetched > timeout) {
            timestamps[key] = now
            return true
        }
        return false
    }

    private fun now(): Long {
        return SystemClock.uptimeMillis()
    }

    @Synchronized
    fun reset(key: Key) {
        timestamps.remove(key)
    }
}