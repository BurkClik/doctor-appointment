package com.example.doctorappointment.domain

import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.common.map
import com.example.doctorappointment.data.remote.HospitalRepository
import com.example.doctorappointment.domain.mapper.HospitalMapper
import com.example.doctorappointment.domain.model.Hospital
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HospitalUseCase @Inject constructor(
    private val hospitalRepository: HospitalRepository,
    private val hospitalMapper: HospitalMapper
) {
    fun getHospital(): Flow<Resource<List<Hospital>>> = hospitalRepository
        .getAllHospital()
        .map {
            it.map { hospitalResponse ->
                hospitalMapper.mapFrom(hospitalResponse)
            }
        }
        .onStart { Resource.Loading }
        .catch { Resource.Error(it) }
        .flowOn(Dispatchers.Default)
}