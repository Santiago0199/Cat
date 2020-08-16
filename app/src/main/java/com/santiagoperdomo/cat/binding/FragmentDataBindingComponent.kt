package com.santiagoperdomo.cat.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

class FragmentDataBindingComponent(fragment: Fragment?) : DataBindingComponent {

    private val fragmentBindingAdapters: FragmentBindingAdapters = FragmentBindingAdapters(fragment!!)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
        return fragmentBindingAdapters
    }

}