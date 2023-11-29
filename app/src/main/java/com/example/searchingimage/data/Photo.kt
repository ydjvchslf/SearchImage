package com.example.searchingimage.data

data class Photo (
    val id: String,
    val width: Int,
    val height: Int,
    val createdTime: String,
    val thumbnailUrl: String,
    val smallUrl: String,
    val user: User
)
