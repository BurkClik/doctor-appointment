package com.example.doctorappointment.domain.model

import com.google.gson.annotations.SerializedName

data class DoctorDemo(
    @SerializedName("about")
    val about: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("hospital")
    val hospital: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_rate")
    val voteRate: String,
    @SerializedName("vote_count")
    val voteCount: String,
)