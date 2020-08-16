package com.santiagoperdomo.cat.ui.breed

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.repository.BreedRepository
import com.santiagoperdomo.cat.repository.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedViewModel @Inject constructor(breedRepository: BreedRepository) : ViewModel() {

    var breeds: LiveData<Resource<List<Breed>>> = breedRepository.loadBreeds()

}