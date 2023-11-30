package com.example.searchingimage.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchingimage.repository.PhotoRepository
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val logTag = DetailViewModel::class.simpleName
    private var photoRepository: PhotoRepository? = null

    init {
        AppDebug.i(logTag, "init-()")
    }

    fun createDb(context: Context) {
        AppDebug.i(logTag, "createDb-()")
        photoRepository = PhotoRepository(context)
    }

    fun loadBookmark(id: String) {
        AppDebug.i(logTag, "loadBookmark-()")
    }

    fun insertPhoto(photo: Photo) {
        AppDebug.i(logTag, "insertPhoto-()")
        viewModelScope.launch {
            val photoId = photoRepository?.insertPhoto(photo)
            AppDebug.i(logTag, "저장된 photoId : $photoId")
        }
    }
}