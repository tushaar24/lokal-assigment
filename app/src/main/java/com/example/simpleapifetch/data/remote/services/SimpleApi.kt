package com.example.simpleapifetch.data.remote.services

import androidx.lifecycle.LiveData
import com.example.simpleapifetch.model.SimpleData
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("/albums/1/photos")
    suspend fun getData(): Response<SimpleData>
}