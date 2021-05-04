package com.example.doctorappointment.data.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Doctor(
    @DrawableRes val doctorImage: Int,
    val doctorName: String,
    @StringRes val doctorCategory: Int,
    @DrawableRes val doctorCategoryIcon: Int,
    val doctorRating: String,
)
