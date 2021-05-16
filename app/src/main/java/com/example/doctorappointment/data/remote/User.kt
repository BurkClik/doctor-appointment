package com.example.doctorappointment.data.remote

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("is_doctor")
    val isDoctor: Boolean,
    @SerializedName("doctor")
    val doctor: Doctor,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
) {
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
}