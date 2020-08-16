package com.santiagoperdomo.cat.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import com.santiagoperdomo.cat.R
import com.santiagoperdomo.cat.databinding.BreedListItemBinding
import com.santiagoperdomo.cat.model.Breed
import java.util.*

class BreedListAdapter(dataBindingComponent: DataBindingComponent, breedClickCallback: BreedClickCallback): DataBoundListAdapter<Breed, BreedListItemBinding>() {

    private var dataBindingComponent: DataBindingComponent
    private var breedClickCallback: BreedClickCallback

    init {
        this.dataBindingComponent = dataBindingComponent
        this.breedClickCallback = breedClickCallback
    }

    override fun createBinding(parent: ViewGroup): BreedListItemBinding {
        val binding: BreedListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.breed_list_item, parent, false, dataBindingComponent)
        binding.root.setOnClickListener {
            val breed = binding.breed
            if(breed != null && breedClickCallback != null){
                breedClickCallback.onClick(breed)
            }
        }
        return binding
    }

    override fun bind(binding: BreedListItemBinding, item: Breed) {
        binding.breed = item
    }

    override fun areItemsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return Objects.equals(oldItem.id, newItem.id) && Objects.equals(oldItem.name, newItem.name)
    }

    override fun areContentsTheSame(oldItem: Breed, newItem: Breed): Boolean {
        return Objects.equals(oldItem.description, newItem.description) && Objects.equals(oldItem.origin, newItem.origin)
    }

    interface BreedClickCallback {
        fun onClick(breed: Breed)
    }
}