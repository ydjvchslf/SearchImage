package com.example.searchingimage.search

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.searchingimage.repository.PhotoRepository
import com.example.searchingimage.repository.entity.Photo
import com.example.searchingimage.retrofit.UnsplashRepository
import com.example.searchingimage.util.AppDebug
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val logTag = SearchViewModel::class.simpleName
    private val unsplashRepository = UnsplashRepository()
    private var photoRepository: PhotoRepository? = null
    var photoList : LiveData<List<Photo>>? = null

    var currentQuery = MutableLiveData("")
    val photos = currentQuery.switchMap { query ->
        unsplashRepository.getSearchResult(query).cachedIn(viewModelScope)
    }

    init {
        AppDebug.i(logTag, "init-()")
    }

    fun createDb(context: Context) {
        AppDebug.i(logTag, "createDb-()")
        photoRepository = PhotoRepository(context)
        photoList = photoRepository?.getAll()
    }

    fun searchPhoto(keyword: String) {
        AppDebug.i(logTag, "searchPhoto-()")
        viewModelScope.launch {
            val photos = unsplashRepository.getSearchResult(keyword).cachedIn(viewModelScope)
            AppDebug.d(logTag, "${photos.value}")
        }
    }

    fun getAll() {
        AppDebug.i(logTag, "searchPhoto-()")
        viewModelScope.launch {
            val list = photoRepository?.getAll()
            AppDebug.i(logTag, "list?.value?.size => ${list?.value?.size}")
        }
    }

}