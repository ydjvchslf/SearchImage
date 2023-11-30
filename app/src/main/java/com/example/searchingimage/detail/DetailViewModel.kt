package com.example.searchingimage.detail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchingimage.repository.PhotoRepository
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.currentBookmarkList
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {

    private val logTag = DetailViewModel::class.simpleName
    private var photoRepository: PhotoRepository? = null
    var crnState = MutableLiveData(false)

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
            checkCurrentData(photo.id)
        }
    }

    fun deletePhoto(photo: Photo) {
        AppDebug.i(logTag, "deletePhoto-()")
        viewModelScope.launch {
            photoRepository?.deleteOne(photo.id)
            checkCurrentData(photo.id)
        }
    }

    fun checkCurrentData(id: String) {
        AppDebug.i(logTag, "checkCurrentData-()")
        viewModelScope.launch {
            currentBookmarkList = photoRepository?.getAll()
        }
        currentBookmarkList?.observeForever { bookmarkList ->
            val isExist = bookmarkList.any { it.id == id }
            AppDebug.i(logTag, "isExist ====> $isExist")
            crnState.value = isExist
        }
    }

}