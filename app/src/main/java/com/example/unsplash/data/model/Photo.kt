package com.example.unsplash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val description: String,
    val authorName: String,
    val uriThumb: String,
    val uriRaw: String
) : Parcelable
