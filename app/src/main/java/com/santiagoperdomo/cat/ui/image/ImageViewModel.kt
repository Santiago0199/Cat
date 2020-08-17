package com.santiagoperdomo.cat.ui.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagoperdomo.cat.model.Image
import com.santiagoperdomo.cat.repository.ImageRepository
import com.santiagoperdomo.cat.repository.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageViewModel @Inject constructor(imageRepository: ImageRepository) : ViewModel() {

    var page: Int = 1
    var limit: Int = 100
    var permissionRequest: Boolean = true
    var imageRepository: ImageRepository

    init {
        this.imageRepository = imageRepository
    }

    fun images(): LiveData<Resource<List<Image>>>{
        return imageRepository.loadImages(page, limit)
    }
}