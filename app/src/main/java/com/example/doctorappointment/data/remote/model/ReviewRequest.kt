package com.example.doctorappointment.data.remote.model

import com.google.gson.annotations.SerializedName

data class ReviewRequest(
    @SerializedName("patient_name")
    val patientName: String,
    val review: String,
    val vote: Float
)