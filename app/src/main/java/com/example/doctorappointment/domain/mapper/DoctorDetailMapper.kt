package com.example.doctorappointment.domain.mapper

import com.example.doctorappointment.common.Mapper
import com.example.doctorappointment.common.ext.capitalizeEachWord
import com.example.doctorappointment.data.remote.model.UserResponse
import com.example.doctorappointment.domain.model.User
import javax.inject.Inject

class DoctorDetailMapper @Inject constructor(private val doctorMapper: DoctorMapper) :
    Mapper<UserResponse, User> {
    override fun mapFrom(type: UserResponse): User {
        return User(
            _id = type._id,
            name = type.name.capitalizeEachWord(),
            city = type.city,
            gender = type.gender,
            isDoctor = type.isDoctor,
            mail = type.mail,
            doctor = doctorMapper.mapFrom(type.doctor)
        )
    }
}