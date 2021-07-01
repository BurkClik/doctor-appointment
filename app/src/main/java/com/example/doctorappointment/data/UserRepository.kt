package com.example.doctorappointment.data

import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.remote.AppointmentDataSource
import com.example.doctorappointment.data.remote.model.AppointmentResponse
import com.example.doctorappointment.data.remote.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor(private val appointmentDataSource: AppointmentDataSource) {
    fun getTopRatedDoctor(count: Int): Flow<Resource<List<UserResponse>>> = flow {
        emit(appointmentDataSource.getTopRatedDoctors(count))
    }.map {
        Resource.Success(it)
    }

    fun getSearchDoctor(doctorQuery: String): Flow<Resource<List<UserResponse>>> = flow {
        emit(appointmentDataSource.getSearchDoctor(doctorQuery = doctorQuery))
    }.map {
        Resource.Success(it)
    }

    fun getSearchHospital(hospitalQuery: String): Flow<Resource<List<UserResponse>>> = flow {
        emit(appointmentDataSource.getSearchHospital(hospitalQuery = hospitalQuery))
    }.map {
        Resource.Success(it)
    }

    fun getUser(user: String): Flow<Resource<List<UserResponse>>> = flow {
        emit(appointmentDataSource.getUserDetail(user))
    }.map {
        Resource.Success(it)
    }

    fun getDoctorDetail(id: String): Flow<Resource<UserResponse>> = flow {
        emit(appointmentDataSource.getDoctorDetail(id))
    }.map {
        Resource.Success(it)
    }

    fun getProfile(id: String): Flow<Resource<AppointmentResponse>> = flow {
        emit(appointmentDataSource.getProfile(id))
    }.map {
        Resource.Success(it)
    }
}