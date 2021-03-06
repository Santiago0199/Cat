package com.santiagoperdomo.cat.repository

import androidx.lifecycle.LiveData
import com.santiagoperdomo.cat.AppExecutors
import com.santiagoperdomo.cat.api.ApiResponse
import com.santiagoperdomo.cat.api.ApiService
import com.santiagoperdomo.cat.db.VoteDao
import com.santiagoperdomo.cat.model.VoteRequest
import com.santiagoperdomo.cat.model.VoteResponse
import com.santiagoperdomo.cat.util.Constants
import com.santiagoperdomo.cat.util.DateUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoteImageRepository @Inject constructor(appExecutors: AppExecutors, apiService: ApiService, voteDao: VoteDao){

    private val voteDao: VoteDao
    private val apiService: ApiService
    private val appExecutors: AppExecutors

    init {
        this.voteDao = voteDao
        this.apiService = apiService
        this.appExecutors = appExecutors
    }

    fun loadVotes(): LiveData<Resource<List<VoteResponse>>> {
        return object : NetworkBoundResource<List<VoteResponse>, List<VoteResponse>>(appExecutors){
            override fun shouldFetch(data: List<VoteResponse>?): Boolean {
                return false
            }
            override fun loadFromDb(): LiveData<List<VoteResponse>> {
                return voteDao.getVotes()
            }
            override fun createCall(): LiveData<ApiResponse<List<VoteResponse>>> {
                TODO("Not yet implemented")
            }
            override fun saveCallResult(item: List<VoteResponse>) {
                TODO("Not yet implemented")
            }
        }.asLiveData()
    }

    fun createVote(voteRequest: VoteRequest): LiveData<Resource<VoteResponse>> {
        return object : NetworkBoundResource<VoteResponse, VoteResponse>(appExecutors){
            override fun shouldFetch(data: VoteResponse?): Boolean {
                return true
            }
            override fun loadFromDb(): LiveData<VoteResponse> {
                return voteDao.getVoteByIdImage(voteRequest.image_id!!)
            }
            override fun createCall(): LiveData<ApiResponse<VoteResponse>> {
                return apiService.createVote(Constants.API_KEY, Constants.CONTENT_TYPE, voteRequest)
            }
            override fun saveCallResult(item: VoteResponse) {
                item.idImage = voteRequest.image_id!!
                item.date = DateUtil.getDateNow(DateUtil.FORMAT_ISO)
                voteDao.insert(item)
            }
        }.asLiveData()
    }

}