package com.example.doctorappointment.domain.mapper

import com.example.doctorappointment.common.Mapper
import com.example.doctorappointment.common.ext.capitalizeEachWord
import com.example.doctorappointment.data.remote.model.Doctor
import com.example.doctorappointment.domain.model.DoctorDemo
import javax.inject.Inject

class DoctorMapper @Inject constructor() : Mapper<Doctor, DoctorDemo> {
    override fun mapFrom(type: Doctor): DoctorDemo {
        return DoctorDemo(
            about = type.about,
            department = type.department.capitalizeEachWord(),
            hospital = type.hospital.capitalizeEachWord(),
            title = type.title.capitalizeEachWord(),
            voteRate = type.voteRate.toString(),
            voteCount = type.voteCount.toString()
        )
    }
}