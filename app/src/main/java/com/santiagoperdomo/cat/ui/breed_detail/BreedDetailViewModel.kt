package com.santiagoperdomo.cat.ui.breed_detail

import androidx.lifecycle.ViewModel
import com.santiagoperdomo.cat.model.Breed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedDetailViewModel @Inject constructor() : ViewModel()  {

    lateinit var breed: Breed

}