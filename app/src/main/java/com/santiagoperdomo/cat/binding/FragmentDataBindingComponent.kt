package com.santiagoperdomo.cat.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

class FragmentDataBindingComponent(fragment: Fragment?) : DataBindingComponent {

    private val fragmentBindingAdapters: FragmentBindingAdapters = FragmentBindingAdapters(fragment!!)

}