package com.example.doctorappointment.data.remote.model

data class SignUpRequest(
    val name: String,
    val mail: String,
    val password: String
)