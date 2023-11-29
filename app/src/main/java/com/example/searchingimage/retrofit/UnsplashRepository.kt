package com.example.searchingimage.retrofit

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.searchingimage.data.response.UnsplashPhoto
import com.example.searchingimage.util.AppDebug
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class UnsplashRepository {

    private val logTag = UnsplashRepository::class.simpleName
    private val unsplashApi = RetrofitClient.unsplashApi

    fun getSearchResult(query: String) =
        //AppDebug.i(logTag, "getSearchResult-()")
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
    ).liveData
}