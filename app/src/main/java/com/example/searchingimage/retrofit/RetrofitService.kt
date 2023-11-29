package com.example.searchingimage.retrofit

import com.example.searchingimage.data.Photo
import retrofit2.http.*

interface RetrofitService {

    @GET("search/photos")
    suspend fun getPhotoList(keyword: String): Result<List<Photo>>

}