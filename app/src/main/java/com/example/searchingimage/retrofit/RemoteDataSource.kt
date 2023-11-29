package com.example.searchingimage.retrofit

import com.example.searchingimage.data.response.UnsplashResponse
import com.example.searchingimage.util.AppDebug
import com.example.searchingimage.util.CLIENT_ID


class RemoteDataSource {

    private val logTag = RemoteDataSource::class.simpleName
    private val unsplashApi = RetrofitClient.unsplashApi

    suspend fun searchPhoto(keyword: String, result: (Boolean?) -> Unit): UnsplashResponse? {
        AppDebug.w(logTag, "searchPhoto-()")

//        val response = retrofitService.getPhotoList(keyword, CLIENT_ID)
//        return when (response) {
//            is Result.Success -> {
//                AppDebug.i(logTag, "Result Success!!")
//                AppDebug.d(logTag, "response.code => ${response.code}")
//                AppDebug.d(logTag, "response.data => ${response.data}")
//                AppDebug.d(logTag, "response.data.results.size => ${response.data.results.size}")
//                result.invoke(true)
//                return response.data
//            }
//            is Result.ApiError -> {
//                AppDebug.i(logTag, "ApiError!!")
//                if (response.code == 5000) { // 로그인에러
//                    AppDebug.i(logTag, "Login error")
//                }
//                result.invoke(false)
//                return null
//            }
//            is Result.NetworkError -> {
//                AppDebug.i(logTag, "NetworkError!!")
//                AppDebug.d(logTag, "response.throwable => ${response.throwable}")
//                result.invoke(false)
//                return null
//            }
//            else -> {
//                result.invoke(false)
//                null
//            }
//        }
        return null
    }
}