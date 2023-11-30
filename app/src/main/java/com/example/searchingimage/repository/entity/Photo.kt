package com.example.searchingimage.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.searchingimage.data.PhotoModel
import com.example.searchingimage.data.response.UnsplashPhoto

@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey(autoGenerate = true) var photo_id: Long, // 고유 PK (id 는 중복이 발생 됨)
    @ColumnInfo var id: String,
    @ColumnInfo var created_at: String,
    @ColumnInfo var width: Int,
    @ColumnInfo var height: Int,
    @ColumnInfo var regular: String,
    @ColumnInfo var thumb: String,
    @ColumnInfo var username: String
){
    override fun toString(): String {
        return "photoId: $photo_id, id: $id, username: $username"
    }

    companion object {
        fun fromUnsplash(unsplashPhoto: UnsplashPhoto) = Photo(
            photo_id = 0,
            id = unsplashPhoto.id,
            created_at = unsplashPhoto.created_at,
            width = unsplashPhoto.width,
            height = unsplashPhoto.height,
            regular = unsplashPhoto.urls.regular,
            thumb = unsplashPhoto.urls.thumb,
            username = unsplashPhoto.user.username
        )
    }
}