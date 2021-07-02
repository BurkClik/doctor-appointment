package com.example.doctorappointment.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
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
    @SerializedName("review")
    val review: List<Review> = ArrayList(),
    @SerializedName("appointment")
    val appointment: List<AppointmentDemo> = ArrayList()
)