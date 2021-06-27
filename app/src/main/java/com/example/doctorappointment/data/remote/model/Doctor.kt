package com.example.doctorappointment.data.remote.model

import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("about")
    val about: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("hospital")
    val hospital: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_rate")
    val voteRate: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
)