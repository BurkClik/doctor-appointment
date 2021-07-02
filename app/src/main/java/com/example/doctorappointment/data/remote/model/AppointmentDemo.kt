package com.example.doctorappointment.data.remote.model

import com.example.doctorappointment.common.BindableItem
import com.google.gson.annotations.SerializedName


data class AppointmentDemo(
    val hour: String,
    val date: String,
    @SerializedName("patient_name")
    val patientName: String,
    @SerializedName("doctor_name")
    val doctorName: String,
) : BindableItem