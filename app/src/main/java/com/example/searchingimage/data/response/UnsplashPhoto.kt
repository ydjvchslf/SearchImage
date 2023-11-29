package com.example.searchingimage.data.response

data class UnsplashPhoto(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
)

data class UnsplashPhotoUrls(
    val regular: String,
    val thumb: String
)

data class UnsplashUser(
    val username: String
)