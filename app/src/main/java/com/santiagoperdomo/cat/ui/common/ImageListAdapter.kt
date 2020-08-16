package com.santiagoperdomo.cat.ui.common

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.databinding.ImageListItemBinding
import com.santiagoperdomo.cat.model.Image
import java.util.*

class ImageListAdapter(dataBindingComponent: DataBindingComponent, imageClickCallback: ImageClickCallback): DataBoundListAdapter<Image, ImageListItemBinding>() {

    private var dataBindingComponent: DataBindingComponent
    private var imageClickCallback: ImageClickCallback

    init {
        this.dataBindingComponent = dataBindingComponent
        this.imageClickCallback = imageClickCallback
    }

    override fun createBinding(parent: ViewGroup): ImageListItemBinding {
        val binding: ImageListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.image_list_item, parent, false, dataBindingComponent)
        binding.btnLike.setOnClickListener {
            val image = binding.image

            binding.btnLike.isEnabled = false
            binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24)
            Handler().postDelayed({
                binding.btnLike.isEnabled = true
                binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }, 500)

            if(image != null && imageClickCallback != null){
                imageClickCallback.onClick(image)
            }
        }

        return binding
    }

    override fun bind(binding: ImageListItemBinding, item: Image) {
        binding.image = item
    }

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return Objects.equals(oldItem.id, newItem.id)
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return Objects.equals(oldItem.url, newItem.url)
    }

    interface ImageClickCallback {
        fun onClick(image: Image)
    }
}