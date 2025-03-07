package com.example.unsplash.ui.photos

import com.example.unsplash.data.network.api.UnsplashApi
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val api: UnsplashApi
) {

    suspend fun requestPhotosList() = api.getPhotos(50)

}