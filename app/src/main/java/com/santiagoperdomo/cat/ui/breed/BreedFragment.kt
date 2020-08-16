package com.santiagoperdomo.cat.ui.breed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.binding.FragmentDataBindingComponent
import com.santiagoperdomo.cat.databinding.FragmentBreedBinding
import com.santiagoperdomo.cat.di.Injectable
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.ui.common.BreedListAdapter
import com.santiagoperdomo.cat.util.AutoClearedValue
import javax.inject.Inject

class BreedFragment : Fragment(), Injectable {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val breedViewModel by lazy {
        ViewModelProvider(this, modelFactory)[BreedViewModel::class.java]
    }

    val dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    lateinit var binding: AutoClearedValue<FragmentBreedBinding>
    lateinit var breedListAdapter: AutoClearedValue<BreedListAdapter>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: FragmentBreedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_breed, container, false, dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = BreedListAdapter(dataBindingComponent, object : BreedListAdapter.BreedClickCallback{
            override fun onClick(breed: Breed) {
                val bundle = bundleOf("breed" to breed)
                findNavController().navigate(R.id.actionDetailFragment, bundle)
            }
        })
        binding.get()!!.breedList.adapter = adapter
        breedListAdapter = AutoClearedValue(this, adapter)
        initBreedList()
    }

    private fun initBreedList() {
        breedViewModel.breeds.observe(viewLifecycleOwner, Observer { breeds ->
            if (breeds.data == null) {
                breedListAdapter.get()!!.replace(null)
            } else {
                breedListAdapter.get()!!.replace(breeds.data)
            }
        })
    }
}

