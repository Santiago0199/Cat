package com.santiagoperdomo.cat.repository

import androidx.lifecycle.LiveData
import com.santiagoperdomo.cat.AppExecutors
import com.santiagoperdomo.cat.api.ApiResponse
import com.santiagoperdomo.cat.api.ApiService
import com.santiagoperdomo.cat.db.BreedDao
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.model.Image
import com.santiagoperdomo.cat.util.Constants
import com.santiagoperdomo.cat.util.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BreedRepository @Inject constructor(appExecutors: AppExecutors, apiService: ApiService, breedDao: BreedDao){

    private val breedDao: BreedDao
    private val apiService: ApiService
    private val appExecutors: AppExecutors

    private val breedListRateLimit = RateLimiter<String>(1, TimeUnit.MINUTES)

    init {
        this.breedDao = breedDao
        this.apiService = apiService
        this.appExecutors = appExecutors
    }

    fun loadBreeds(): LiveData<Resource<List<Breed>>> {
        return object : NetworkBoundResource<List<Breed>, List<Breed>>(appExecutors) {
            override fun shouldFetch(data: List<Breed>?): Boolean {
                return data == null || data.isEmpty() || breedListRateLimit.shouldFetch("ListBreed")
            }
            override fun loadFromDb(): LiveData<List<Breed>> {
                return breedDao.getBreeds()
            }
            override fun createCall(): LiveData<ApiResponse<List<Breed>>> {
                return apiService.getBreeds(Constants.API_KEY)
            }
            override fun saveCallResult(item: List<Breed>) {
                breedDao.insert(item)
            }
        }.asLiveData()
    }

    fun loadImageByBreedId(breed: Breed): LiveData<Resource<String>> {
        return object : NetworkBoundResource<String, List<Image>>(appExecutors) {
            override fun shouldFetch(data: String?): Boolean {
                return data == null || data.isEmpty()
            }
            override fun loadFromDb(): LiveData<String> {
                return breedDao.getBreedByUrlImage(breed.id)
            }
            override fun createCall(): LiveData<ApiResponse<List<Image>>> {
                return apiService.getImageByBreedId(Constants.API_KEY, breed.id)
            }
            override fun saveCallResult(item: List<Image>) {
                breed.urlImage = item[0].url
                breedDao.insert(breed)
            }
        }.asLiveData()
    }

}