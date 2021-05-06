package com.example.doctorappointment.data.remote

data class User(
    val _id: String,
    val city: String,
    val gender: String,
    val is_doctor: Boolean,
    val mail: String,
    val name: String,
)