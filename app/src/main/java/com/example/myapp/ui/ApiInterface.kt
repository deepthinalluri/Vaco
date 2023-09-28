package com.example.myapp.ui

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("b/WN0G")
    suspend fun getData(): Response<MyData>
}