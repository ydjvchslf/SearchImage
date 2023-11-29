package com.example.searchingimage.retrofit

import com.example.searchingimage.data.response.UnsplashResponse
import retrofit2.http.*

interface UnsplashApi {

    @GET("search/photos")
    suspend fun getPhotoList (
        @Query ("client_id") id: String,
        @Query ("query") keyword: String,
        @Query ("page") page: Int,
        @Query ("per_page") perPage: Int,
    ): UnsplashResponse

}