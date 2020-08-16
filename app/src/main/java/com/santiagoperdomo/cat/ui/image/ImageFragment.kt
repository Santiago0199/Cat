package com.santiagoperdomo.cat.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.binding.FragmentDataBindingComponent
import com.santiagoperdomo.cat.databinding.FragmentImageBinding
import com.santiagoperdomo.cat.di.Injectable
import com.santiagoperdomo.cat.model.Image
import com.santiagoperdomo.cat.repository.Status
import com.santiagoperdomo.cat.ui.common.ImageListAdapter
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
    lateinit var imageListAdapter: AutoClearedValue<ImageListAdapter>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: FragmentImageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false, dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ImageListAdapter(dataBindingComponent, object : ImageListAdapter.ImageClickCallback{
            override fun onClick(image: Image) {
                eventItemImage(image)
            }
        })
        binding.get()!!.imagesList.adapter = adapter
        binding.get()!!.imagesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                eventScrollListImage(recyclerView)
            }
        })
        imageListAdapter = AutoClearedValue(this, adapter)
        initImagesList()
    }

    private fun initImagesList() {
        imageViewModel.images().observe(viewLifecycleOwner, Observer { images ->
            if (images.data == null && images.status != Status.LOADING) imageListAdapter.get()!!.replace(null)
            if (images.status != Status.LOADING) {
                imageViewModel.permissionRequest = true
                imageListAdapter.get()!!.replace(images.data)
            }
        })
    }

    fun eventItemImage(image: Image){

    }

    fun eventScrollListImage(recyclerView: RecyclerView){
        val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
        val pos = layoutManager!!.findLastCompletelyVisibleItemPosition()
        val numItems: Int = binding.get()!!.imagesList.adapter!!.itemCount
        if(pos >= numItems - 1 && imageViewModel.permissionRequest){
            imageViewModel.permissionRequest = false
            imageViewModel.page += 1
            initImagesList()
        }
    }
}