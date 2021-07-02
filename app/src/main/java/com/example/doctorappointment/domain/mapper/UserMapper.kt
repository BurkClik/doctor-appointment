package com.example.doctorappointment.domain.mapper

import com.example.doctorappointment.common.Mapper
import com.example.doctorappointment.common.ext.capitalizeEachWord
import com.example.doctorappointment.data.remote.model.UserResponse
import com.example.doctorappointment.domain.model.DoctorDemo
import com.example.doctorappointment.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor(
    private val doctorMapper: DoctorMapper,
    private val reviewMapper: ReviewMapper
) :
    Mapper<List<UserResponse>, List<User>> {
    override fun mapFrom(type: List<UserResponse>): List<User> {
        return type.map {
            User(
                _id = it._id,
                city = it.city,
                gender = it.gender,
                mail = it.mail,
                name = it.name.capitalizeEachWord(),
                isDoctor = it.isDoctor,
                doctor = doctorMapper.mapFrom(it.doctor),
                review = reviewMapper.mapFrom(it.review),
                appointment = it.appointment,
            )
        }
    }
}