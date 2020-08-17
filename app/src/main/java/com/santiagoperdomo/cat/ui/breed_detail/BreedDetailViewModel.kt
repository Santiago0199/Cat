package com.santiagoperdomo.cat.ui.breed_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.repository.BreedRepository
import com.santiagoperdomo.cat.repository.ImageRepository
import com.santiagoperdomo.cat.repository.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedDetailViewModel @Inject constructor(breedRepository: BreedRepository) : ViewModel()  {

    var breed: MutableLiveData<Breed>
    var breedRepository : BreedRepository

    init {
        breed = MutableLiveData()
        this.breedRepository = breedRepository
    }

    fun image(): LiveData<Resource<String>> {
        return breedRepository.loadImageByBreedId(breed.value!!)
    }

}