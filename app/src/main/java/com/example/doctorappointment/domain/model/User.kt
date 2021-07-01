package com.example.doctorappointment.domain.model

import com.example.doctorappointment.common.BindableItem
import com.example.doctorappointment.data.remote.model.AppointmentDemo
import com.example.doctorappointment.data.remote.model.AppointmentRequest
import com.example.doctorappointment.data.remote.model.Doctor

data class User(
    val _id: String,
    val city: String,
    val gender: String,
    val isDoctor: Boolean,
    val doctor: DoctorDemo,
    val mail: String,
    val name: String
) : BindableItem