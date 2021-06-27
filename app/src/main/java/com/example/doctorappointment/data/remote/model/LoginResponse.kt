package com.example.doctorappointment.data.remote.model

data class LoginResponse(
    val _id: String,
    val name: String,
    val mail: String,
    val password: String,
    val city: String,
    val gender: String,
    val birthday: String,
    val is_doctor: Boolean,
    val doctor: Doctor,
    val token: String
    )
