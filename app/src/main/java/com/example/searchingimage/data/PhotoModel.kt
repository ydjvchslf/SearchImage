package com.example.searchingimage.data

import com.example.searchingimage.repository.entity.Photo

data class PhotoModel(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val regular: String,
    val thumb: String,
    val username: String
) {
    companion object {
        fun fromEntity(entity: Photo) = PhotoModel (
            id = entity.id,
            created_at = entity.created_at,
            width = entity.width,
            height = entity.height,
            regular = entity.regular,
            thumb = entity.thumb,
            username = entity.username
        )
    }
}
