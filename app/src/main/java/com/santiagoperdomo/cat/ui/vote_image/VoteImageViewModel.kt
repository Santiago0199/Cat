package com.santiagoperdomo.cat.ui.vote_image

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.santiagoperdomo.cat.model.VoteRequest
import com.santiagoperdomo.cat.model.VoteResponse
import com.santiagoperdomo.cat.repository.Resource
import com.santiagoperdomo.cat.repository.VoteImageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoteImageViewModel @Inject constructor(voteImageRepository: VoteImageRepository) : ViewModel() {

    var voteImageRepository: VoteImageRepository

    init {
        this.voteImageRepository = voteImageRepository
    }

    fun createVote(voteRequest: VoteRequest): LiveData<Resource<VoteResponse>> {
        return voteImageRepository.createVote(voteRequest)
    }

}