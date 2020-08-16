package com.santiagoperdomo.cat.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.binding.FragmentDataBindingComponent
import com.santiagoperdomo.cat.databinding.FragmentImageBinding
import com.santiagoperdomo.cat.di.Injectable
import com.santiagoperdomo.cat.ui.vote_image.VoteImageViewModel
import com.santiagoperdomo.cat.util.AutoClearedValue
import javax.inject.Inject

class ImageFragment : Fragment(), Injectable {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val imageViewModel by lazy {
        ViewModelProvider(this, modelFactory)[ImageViewModel::class.java]
    }

    private val voteImageViewModel by lazy {
        ViewModelProvider(this, modelFactory)[VoteImageViewModel::class.java]
    }

    val dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    lateinit var binding: AutoClearedValue<FragmentImageBinding>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: FragmentImageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false, dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }
}