package com.example.searchingimage.bookmark

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchingimage.detail.DetailViewModel
import com.example.searchingimage.repository.PhotoRepository
import com.example.searchingimage.util.AppDebug

class BookmarkViewModel: ViewModel() {

    private val logTag = BookmarkViewModel::class.simpleName
    private var photoRepository: PhotoRepository? = null

    init {
        AppDebug.i(logTag, "init-()")
    }

    fun createDb(context: Context) {
        AppDebug.i(logTag, "createDb-()")
        photoRepository = PhotoRepository(context)
    }
}