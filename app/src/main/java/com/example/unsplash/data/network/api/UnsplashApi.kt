package com.example.unsplash.data.network.api

import com.example.unsplash.data.network.dto.PhotoItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getPhotos(
        @Query("per_page") quality: Int,
    ): Response<List<PhotoItemResponse>>

}