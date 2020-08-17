package com.santiagoperdomo.cat.ui.image

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.binding.FragmentDataBindingComponent
import com.santiagoperdomo.cat.databinding.FragmentImageBinding
import com.santiagoperdomo.cat.di.Injectable
import com.santiagoperdomo.cat.model.Image
import com.santiagoperdomo.cat.model.VoteRequest
import com.santiagoperdomo.cat.repository.Status
import com.santiagoperdomo.cat.ui.common.ImageListAdapter
import com.santiagoperdomo.cat.ui.vote_image.VoteImageViewModel
import com.santiagoperdomo.cat.util.AutoClearedValue
import com.santiagoperdomo.cat.util.Constants
import com.santiagoperdomo.cat.util.SharedPreferencesManager
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
    private var progressDialog: SweetAlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: FragmentImageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false, dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initProgress()
        val adapter = ImageListAdapter(dataBindingComponent, object : ImageListAdapter.ImageClickCallback{
            override fun onClick(image: Image) {
                eventItemImage(image, context!!)
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
            if(images.status != Status.LOADING){
                if (images.status == Status.LOADING) progressDialog!!.show() else progressDialog!!.dismiss()
                if (images.data == null) imageListAdapter.get()!!.replace(null)
                imageViewModel.permissionRequest = true
                imageListAdapter.get()!!.replace(images.data)
            }
        })
    }

    fun eventItemImage(image: Image, context: Context){
        val subId = SharedPreferencesManager.getSomeStringValue(context, Constants.SUB_ID)
        voteImageViewModel.createVote(VoteRequest(image.id, subId)).observe(viewLifecycleOwner, Observer { voteResponse ->
            if(voteResponse.status != Status.SUCCESS && voteResponse.status != Status.LOADING){
                Toast.makeText(context, resources.getString(R.string.no_se_pudo_realizar_el_voto), Toast.LENGTH_SHORT).show()
            }
        })
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

    private fun initProgress(){
        progressDialog = SweetAlertDialog(context)
        progressDialog!!.setTitle(getString(R.string.cargando))
        progressDialog!!.setCancelable(false)
        progressDialog!!.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        progressDialog!!.show()
    }
}