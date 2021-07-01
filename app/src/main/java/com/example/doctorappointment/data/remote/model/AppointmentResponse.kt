package com.example.doctorappointment.data.remote.model

import com.example.doctorappointment.common.BindableItem
import com.google.gson.annotations.SerializedName

data class AppointmentResponse(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("is_doctor")
    val isDoctor: Boolean,
    @SerializedName("doctor")
    val doctor: Doctor,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("appointment")
    val appointment: List<AppointmentDemo> = ArrayList()
)

data class AppointmentDemo(
    val hour: String,
    val date: String,
    @SerializedName("patient_name")
    val patientName: String,
    @SerializedName("doctor_name")
    val doctorName: String,
) : BindableItem
