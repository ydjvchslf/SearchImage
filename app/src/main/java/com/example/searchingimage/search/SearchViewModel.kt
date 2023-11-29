package com.example.searchingimage.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.flatMap
import com.example.searchingimage.data.Photo
import com.example.searchingimage.retrofit.RemoteDataSource
import com.example.searchingimage.retrofit.UnsplashRepository
import com.example.searchingimage.util.AppDebug
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {

    private val logTag = SearchViewModel::class.simpleName
    //private val remoteDataSource = RemoteDataSource()
    private val unsplashRepository = UnsplashRepository()

    var currentQuery = MutableLiveData("")
    val photos = currentQuery.switchMap { query ->
        unsplashRepository.getSearchResult(query).cachedIn(viewModelScope)
    }

    init {
        AppDebug.i(logTag, "init-()")
    }

    fun searchPhoto(keyword: String) {
        AppDebug.i(logTag, "searchPhoto-()")
        viewModelScope.launch {
            val photos = unsplashRepository.getSearchResult(keyword).cachedIn(viewModelScope)
            AppDebug.d(logTag, "${photos.value}")

        }
    }

}