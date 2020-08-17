package com.santiagoperdomo.cat.api

import androidx.lifecycle.LiveData
import com.santiagoperdomo.cat.model.Breed
import com.santiagoperdomo.cat.model.Image
import com.santiagoperdomo.cat.model.VoteRequest
import com.santiagoperdomo.cat.model.VoteResponse
import retrofit2.http.*

interface ApiService {

    @GET(Endpoints.GET_BREEDS)
    fun getBreeds(@Header("x-api-key") apiKey: String): LiveData<ApiResponse<List<Breed>>>

    @GET(Endpoints.GET_IMAGES)
    fun getImages(@Header("x-api-key") apiKey: String,
                  @Query("page") page: Int,
                  @Query("limit") limit: Int): LiveData<ApiResponse<List<Image>>>

    @GET(Endpoints.GET_IMAGES)
    fun getImageByBreedId(@Header("x-api-key") apiKey: String,
                  @Query("breed_id") breed_id: String): LiveData<ApiResponse<List<Image>>>

    @POST(Endpoints.CREATE_VOTE)
    fun createVote(@Header("x-api-key") apiKey: String,
                   @Header("Content-Type") contentType: String,
                   @Body voteRequest: VoteRequest): LiveData<ApiResponse<VoteResponse>>


}