package com.example.searchingimage.repository

import android.content.Context
import androidx.room.Room
import com.example.searchingimage.repository.db.AppDatabase
import com.example.searchingimage.repository.entity.Photo

class PhotoRepository(mContext: Context) {
    //private val appDBInstant = GlobalApplication.databaseInstance.photoDao()
    //private var db = AppDatabase.getInstance(mContext)
    private val db = Room.databaseBuilder(mContext, AppDatabase::class.java, "photoDB").allowMainThreadQueries().build()

    suspend fun insertPhoto(photo: Photo): Long = db.photoDao().insertPhoto(photo)
    suspend fun deletePhoto() = db.photoDao().deleteAll()
    suspend fun deleteOne(id: String) = db.photoDao().deleteOne(id)

    fun getAll() = db.photoDao().getAll()
}