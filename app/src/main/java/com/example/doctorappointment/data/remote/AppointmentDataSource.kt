package com.example.doctorappointment.data.remote

import com.example.doctorappointment.data.remote.api.ApiService
import com.example.doctorappointment.data.remote.model.AppointmentResponse
import com.example.doctorappointment.data.remote.model.HospitalResponse
import com.example.doctorappointment.data.remote.model.UserResponse
import javax.inject.Inject

class AppointmentDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getTopRatedDoctors(count: Int): List<UserResponse> =
        apiService.getTopRatedDoctors(count)

    suspend fun getSearchDoctor(doctorQuery: String): List<UserResponse> =
        apiService.searchDoctor(doctorQuery = doctorQuery)

    suspend fun getSearchHospital(hospitalQuery: String): List<UserResponse> =
        apiService.searchHospital(hospitalQuery = hospitalQuery)

    suspend fun getUserDetail(user: String): List<UserResponse> = apiService.getUser(user)

    suspend fun getDoctorDetail(id: String): UserResponse = apiService.getDoctor(id)

    suspend fun getProfile(id: String): AppointmentResponse = apiService.getProfile(id)

    suspend fun getHospital(): List<HospitalResponse> = apiService.getAllHospitals()
}