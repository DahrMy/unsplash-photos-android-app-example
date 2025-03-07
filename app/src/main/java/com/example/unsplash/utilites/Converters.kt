package com.example.unsplash.utilites

import com.example.unsplash.data.model.Photo
import com.example.unsplash.data.network.dto.PhotoItemResponse

fun PhotoItemResponse.toModel() = Photo(
    id = this.id,
    description = this.description ?: this.altDescription ?: "",
    uriRaw = this.urls.raw,
    uriThumb = this.urls.thumb,
    authorName = this.user.name
)

fun List<PhotoItemResponse>.toModel(): List<Photo> {
    val list = mutableListOf<Photo>()
    this.forEach { list.add(it.toModel()) }
    return list
}