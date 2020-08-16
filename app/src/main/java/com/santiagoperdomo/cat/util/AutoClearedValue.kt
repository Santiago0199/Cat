package com.santiagoperdomo.cat.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class AutoClearedValue<T>(fragment: Fragment, value: T) {

    private var value: T?

    fun get(): T? {
        return value
    }

    init {
        val fragmentManager = fragment.fragmentManager
        fragmentManager!!.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentDestroyed(
                    fm: FragmentManager,
                    f: Fragment
                ) {
                    if (f === fragment) {
                        this@AutoClearedValue.value = null
                        fragmentManager.unregisterFragmentLifecycleCallbacks(this)
                    }
                }
            }, false
        )
        this.value = value
    }
}