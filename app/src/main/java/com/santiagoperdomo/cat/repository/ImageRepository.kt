package com.santiagoperdomo.cat.repository

import androidx.lifecycle.LiveData
import com.santiagoperdomo.cat.AppExecutors
import com.santiagoperdomo.cat.api.ApiResponse
import com.santiagoperdomo.cat.api.ApiService
import com.santiagoperdomo.cat.db.ImageDao
import com.santiagoperdomo.cat.model.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(appExecutors: AppExecutors, apiService: ApiService, imageDao: ImageDao){

    private val imageDao: ImageDao
    private val apiService: ApiService
    private val appExecutors: AppExecutors

    init {
        this.imageDao = imageDao
        this.apiService = apiService
        this.appExecutors = appExecutors
    }

    fun loadImages(page: Int, limit: Int): LiveData<Resource<List<Image>>> {
        return object : NetworkBoundResource<List<Image>, List<Image>>(appExecutors) {
            override fun shouldFetch(data: List<Image>?): Boolean {
                return true
            }
            override fun loadFromDb(): LiveData<List<Image>> {
                return imageDao.getImages()
            }
            override fun createCall(): LiveData<ApiResponse<List<Image>>> {
                return apiService.getImages(page, limit)
            }
            override fun saveCallResult(item: List<Image>) {
                imageDao.insert(item)
            }
        }.asLiveData()
    }
}