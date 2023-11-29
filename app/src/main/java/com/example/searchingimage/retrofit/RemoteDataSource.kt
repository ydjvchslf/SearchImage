package com.example.searchingimage.retrofit

import com.example.searchingimage.data.Photo
import com.example.searchingimage.util.AppDebug


class RemoteDataSource {

    private val logTag = RemoteDataSource::class.simpleName
    private val retrofitService = RetrofitClient.retrofitService

    suspend fun searchPhoto(keyword: String, result: (Boolean?) -> Unit): List<Photo>? {
        AppDebug.w(logTag, "searchPhoto-()")

        val response = retrofitService.getPhotoList(keyword)
        return when (response) {
            is Result.Success -> {
                AppDebug.i(logTag, "Result Success!!")
                AppDebug.d(logTag, "response.code => ${response.code}")
                AppDebug.d(logTag, "response.data => ${response.data}")
                result.invoke(true)
                return response.data
            }
            is Result.ApiError -> {
                AppDebug.i(logTag, "ApiError!!")
                if (response.code == 5000) { // 로그인에러
                    AppDebug.i(logTag, "Login error")
                }
                result.invoke(false)
                return null
            }
            is Result.NetworkError -> {
                AppDebug.i(logTag, "NetworkError!!")
                AppDebug.d(logTag, "response.throwable => ${response.throwable}")
                result.invoke(false)
                return null
            }
            else -> {
                result.invoke(false)
                null
            }
        }
    }
}