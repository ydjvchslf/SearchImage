package com.example.searchingimage.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.searchingimage.repository.entity.Photo

val fragTitle = MutableLiveData("")
var currentBookmarkList : LiveData<List<Photo>>? = null

val BASE_URL = "https://api.unsplash.com"
val CLIENT_ID = "I8UBuRXH62hCqOiAjpIygpDV0NTNhOfKmvMchS1sYwM"