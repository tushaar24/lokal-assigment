package com.example.simpleapifetch.Communicator

import com.example.simpleapifetch.data.remote.services.SimpleApi
import com.example.simpleapifetch.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Communicator {
    companion object{
        fun getRemoteApiServices() : SimpleApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SimpleApi::class.java)
        }
    }
}