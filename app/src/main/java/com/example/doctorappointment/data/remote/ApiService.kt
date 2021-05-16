package com.example.doctorappointment.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/doctors")
    fun getDoctors() : Call<List<User>>

    @GET("/toprated/doctors")
    fun getTopRatedDoctors(@Query("count") count: Int) : Call<List<User>>

    @GET("/search/doctor")
    fun searchDoctor(@Query("doctor") doctor: String) : Call<List<User>>
}