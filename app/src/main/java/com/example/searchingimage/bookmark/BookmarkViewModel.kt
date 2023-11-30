package com.example.searchingimage.bookmark

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchingimage.detail.DetailViewModel
import com.example.searchingimage.repository.PhotoRepository
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.currentBookmarkList
import kotlinx.coroutines.launch

class BookmarkViewModel: ViewModel() {

    private val logTag = BookmarkViewModel::class.simpleName
    private var photoRepository: PhotoRepository? = null
    var bookmarkList : LiveData<List<Photo>>? = null

    init {
        AppDebug.i(logTag, "init-()")
    }

    fun createDb(context: Context) {
        AppDebug.i(logTag, "createDb-()")
        photoRepository = PhotoRepository(context)
//        bookmarkList = photoRepository?.getAll()
//        currentBookmarkList = bookmarkList
        AppDebug.i(logTag, "createDb-() 완료")
    }
}