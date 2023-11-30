package com.example.searchingimage.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.searchingimage.repository.dao.PhotoDao
import com.example.searchingimage.repository.entity.Photo

@Database(
    entities = [Photo::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}