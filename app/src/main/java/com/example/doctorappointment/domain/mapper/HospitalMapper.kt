package com.example.doctorappointment.domain.mapper

import com.example.doctorappointment.common.Mapper
import com.example.doctorappointment.data.remote.model.HospitalResponse
import com.example.doctorappointment.domain.model.Hospital
import javax.inject.Inject

class HospitalMapper @Inject constructor() : Mapper<List<HospitalResponse>, List<Hospital>> {
    override fun mapFrom(type: List<HospitalResponse>): List<Hospital> {
        return type.map {
            Hospital(
                id = it._id,
                name = it.name,
                city = it.city,
            )
        }
    }
}