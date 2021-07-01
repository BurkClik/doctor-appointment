package com.example.doctorappointment.data.remote

import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.remote.model.HospitalResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HospitalRepository @Inject constructor(private val appointmentDataSource: AppointmentDataSource) {
    fun getAllHospital(): Flow<Resource<List<HospitalResponse>>> = flow {
        emit(appointmentDataSource.getHospital())
    }.map {
        Resource.Success(it)
    }
}