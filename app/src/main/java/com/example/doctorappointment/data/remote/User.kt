package com.example.doctorappointment.data.remote

data class User(
    val _id: String,
    val city: String,
    val gender: String,
    val is_doctor: Boolean,
    val doctor: Doctor,
    val mail: String,
    val name: String,
) {
    data class Doctor(
        val about: String,
        val department: String,
        val hospital: String,
        val title: String
    )
}