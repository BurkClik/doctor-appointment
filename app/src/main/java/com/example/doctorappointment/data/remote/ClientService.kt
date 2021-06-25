package com.example.doctorappointment.data.remote

import retrofit2.create
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientService {
    private const val BASE_URL = "http://192.168.1.33:8080/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}