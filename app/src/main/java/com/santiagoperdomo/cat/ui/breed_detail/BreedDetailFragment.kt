package com.santiagoperdomo.cat.ui.breed_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.binding.FragmentDataBindingComponent
import com.santiagoperdomo.cat.databinding.FragmentBreedDetailBinding
import com.santiagoperdomo.cat.di.Injectable
import com.santiagoperdomo.cat.util.AutoClearedValue
import javax.inject.Inject

class BreedDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val breedDetailViewModel by lazy {
        ViewModelProvider(this, modelFactory)[BreedDetailViewModel::class.java]
    }

    val dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    lateinit var binding: AutoClearedValue<FragmentBreedDetailBinding>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: FragmentBreedDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_breed_detail, container, false, dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }
}