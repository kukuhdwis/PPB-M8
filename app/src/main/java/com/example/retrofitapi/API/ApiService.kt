package com.example.retrofitapi.API

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("datamahasiswa/")
    fun getMahasiswa(): Call<ApiResponse>
}