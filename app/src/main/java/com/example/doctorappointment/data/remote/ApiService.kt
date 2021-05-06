package com.example.doctorappointment.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/doctors")
    fun getDoctors() : Call<List<User>>
}