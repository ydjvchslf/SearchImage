package com.example.searchingimage.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable

@Parcelize
data class UnsplashPhotoUrls(
    val regular: String,
    val thumb: String
) : Parcelable

@Parcelize
data class UnsplashUser(
    val username: String
) : Parcelable