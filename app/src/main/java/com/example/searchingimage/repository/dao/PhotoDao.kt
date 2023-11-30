package com.example.searchingimage.repository.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.searchingimage.repository.entity.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo ORDER BY photo_id DESC")
    fun getAll(): LiveData<List<Photo>>
//
//    @Query("SELECT * FROM photo WHERE isLike = :isLike")
//    fun getAllFavorite(isLike: Boolean): LiveData<List<Photo>>

    @Insert
    suspend fun insertPhoto(photo: Photo): Long

    @Query("DELETE FROM photo")
    suspend fun deleteAll()

    @Query("DELETE FROM photo WHERE id = :id")
    suspend fun deleteOne(id: String)
}