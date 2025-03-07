package com.example.unsplash.data.network.dto


import com.google.gson.annotations.SerializedName

data class PhotoItemResponse(
    val id: String,
    val width: Int,
    val height: Int,
    val description: String?,
    @SerializedName("alt_description")
    val altDescription: String?,
    val urls: Urls,
    val user: User
) {

    data class Urls(
        val raw: String,
        val thumb: String,
    )

    data class User(
        val id: String,
        val name: String,
    )

}