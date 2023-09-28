package com.example.myapp

import com.example.myapp.ui.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetroInstance {

    val base_URL = "https://www.jsonkeeper.com/"

    fun getRtroInstance():ApiInterface{

        val retrofitInstance = Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        return retrofitInstance
    }

}