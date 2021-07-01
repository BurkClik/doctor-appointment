package com.example.doctorappointment.data.remote.model

data class AppointmentRequest(
    val hour: String,
    val date: String,
    val patientName: String,
    val doctorName: String
)
