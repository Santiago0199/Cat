package com.santiagoperdomo.cat.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.databinding.VoteImageListItemBinding
import com.santiagoperdomo.cat.model.VoteResponse
import java.util.*

class VoteImageListAdapter(dataBindingComponent: DataBindingComponent): DataBoundListAdapter<VoteResponse, VoteImageListItemBinding>() {

    private var dataBindingComponent: DataBindingComponent

    init {
        this.dataBindingComponent = dataBindingComponent
    }

    override fun createBinding(parent: ViewGroup): VoteImageListItemBinding {
        val binding: VoteImageListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.vote_image_list_item, parent, false, dataBindingComponent)
        return binding
    }

    override fun bind(binding: VoteImageListItemBinding, item: VoteResponse) {
        binding.vote = item
    }

    override fun areItemsTheSame(oldItem: VoteResponse, newItem: VoteResponse): Boolean {
        return Objects.equals(oldItem.id, newItem.id)
    }

    override fun areContentsTheSame(oldItem: VoteResponse, newItem: VoteResponse): Boolean {
        return Objects.equals(oldItem.date, newItem.date)
    }
}