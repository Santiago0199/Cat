package com.santiagoperdomo.cat.ui.vote_image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.binding.FragmentDataBindingComponent
import com.santiagoperdomo.cat.databinding.FragmentVoteImageBinding
import com.santiagoperdomo.cat.di.Injectable
import com.santiagoperdomo.cat.repository.Status
import com.santiagoperdomo.cat.ui.common.VoteImageListAdapter
import com.santiagoperdomo.cat.util.AutoClearedValue
import javax.inject.Inject

class VoteImageFragment : Fragment(), Injectable {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    private val voteImageViewModel by lazy {
        ViewModelProvider(this, modelFactory)[VoteImageViewModel::class.java]
    }

    val dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    lateinit var binding: AutoClearedValue<FragmentVoteImageBinding>
    lateinit var voteImageListAdapter: AutoClearedValue<VoteImageListAdapter>
    private var progressDialog: SweetAlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: FragmentVoteImageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vote_image, container, false, dataBindingComponent)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initProgress()
        val adapter = VoteImageListAdapter(dataBindingComponent)
        binding.get()!!.votesList.adapter = adapter
        voteImageListAdapter = AutoClearedValue(this, adapter)
        initVoteImageList()
    }

    private fun initVoteImageList() {
        voteImageViewModel.votes.observe(viewLifecycleOwner, Observer { votes ->
            if (votes.status == Status.LOADING) progressDialog!!.show() else progressDialog!!.dismiss()
            if (votes.data == null) voteImageListAdapter.get()!!.replace(null)
            voteImageListAdapter.get()!!.replace(votes.data)
        })
    }

    private fun initProgress(){
        progressDialog = SweetAlertDialog(context)
        progressDialog!!.setTitle(getString(R.string.cargando))
        progressDialog!!.setCancelable(false)
        progressDialog!!.changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
        progressDialog!!.show()
    }
}
