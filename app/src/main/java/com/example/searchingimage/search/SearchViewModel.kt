package com.example.searchingimage.search

import androidx.lifecycle.ViewModel
import com.example.searchingimage.retrofit.RemoteDataSource
import com.example.searchingimage.util.AppDebug

class SearchViewModel: ViewModel() {

    private val logTag = SearchViewModel::class.simpleName
    private val remoteDataSource = RemoteDataSource()

    init {
        AppDebug.i(logTag, "init-()")
        searchPhoto()
    }

    private fun searchPhoto() {

    }

}