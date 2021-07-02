package com.example.doctorappointment.data.remote.api

import com.example.doctorappointment.data.remote.model.AppointmentRequest
import com.example.doctorappointment.data.remote.model.AppointmentResponse
import com.example.doctorappointment.data.remote.model.HospitalResponse
import com.example.doctorappointment.data.remote.model.LoginRequest
import com.example.doctorappointment.data.remote.model.LoginResponse
import com.example.doctorappointment.data.remote.model.ReviewRequest
import com.example.doctorappointment.data.remote.model.SignUpRequest
import com.example.doctorappointment.data.remote.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("/doctors")
    suspend fun getDoctors(): List<UserResponse>

    @GET("/toprated/doctors")
    suspend fun getTopRatedDoctors(@Query("count") count: Int): List<UserResponse>

    @GET("/search/doctor")
    suspend fun searchDoctor(@Query("doctor") doctorQuery: String): List<UserResponse>

    @GET("/search/hospital")
    suspend fun searchHospital(@Query("hospital") hospitalQuery: String): List<UserResponse>

    @GET("/hospital")
    suspend fun getAllHospitals(): List<HospitalResponse>

    @GET("/user")
    suspend fun getUser(@Query("mail") user: String): List<UserResponse>

    @GET("/doctor")
    suspend fun getProfile(@Query("_id") _id: String): AppointmentResponse

    @GET("/doctor")
    suspend fun getDoctor(@Query("_id") _id: String): UserResponse

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/signup")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<LoginResponse>

    @POST("/review")
    suspend fun review(
        @Query("_id") _id: String,
        @Body reviewRequest: ReviewRequest
    ): Response<AppointmentResponse>

    @POST("/appointment")
    suspend fun getAppointment(
        @Query("_id") _id: String,
        @Body appointmentRequest: AppointmentRequest
    ): Response<AppointmentResponse>
}