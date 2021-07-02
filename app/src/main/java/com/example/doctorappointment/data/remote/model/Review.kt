package com.example.doctorappointment.data.remote.model

import com.example.doctorappointment.common.BindableItem
import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("patient_name")
    val patientName: String,
    val review: String,
    val vote: Float
) : BindableItem
