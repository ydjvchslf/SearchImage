package com.example.searchingimage.data.response

import android.os.Parcelable
import com.example.searchingimage.repository.entity.Photo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    companion object {
        fun fromPhoto(photo: Photo) = UnsplashPhoto (
            id = photo.id,
            created_at = photo.created_at,
            width = photo.width,
            height = photo.height,
            urls = UnsplashPhotoUrls(photo.regular, photo.thumb),
            user = UnsplashUser(photo.username)
        )
    }
}

@Parcelize
data class UnsplashPhotoUrls(
    val regular: String,
    val thumb: String
) : Parcelable

@Parcelize
data class UnsplashUser(
    val username: String
) : Parcelable