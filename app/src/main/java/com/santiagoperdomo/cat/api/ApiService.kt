package com.santiagoperdomo.cat.api

import androidx.lifecycle.LiveData
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.model.Image
import com.santiagoperdomo.cat.model.VoteRequest
import com.santiagoperdomo.cat.model.VoteResponse
import retrofit2.http.*

interface ApiService {

    @Headers("x-api-key: c5449de4-20f0-4e81-b41c-65be88dcb88f")
    @GET(Endpoints.GET_BREEDS)
    fun getBreeds(): LiveData<ApiResponse<List<Breed>>>

    @Headers("x-api-key: c5449de4-20f0-4e81-b41c-65be88dcb88f")
    @GET(Endpoints.GET_IMAGES)
    fun getImages(@Query("page") page: Int, @Query("limit") limit: Int): LiveData<ApiResponse<List<Image>>>

    @Headers("x-api-key: c5449de4-20f0-4e81-b41c-65be88dcb88f", "Content-Type: application/json")
    @POST(Endpoints.CREATE_VOTE)
    fun createVote(@Body voteRequest: VoteRequest): LiveData<ApiResponse<VoteResponse>>


}