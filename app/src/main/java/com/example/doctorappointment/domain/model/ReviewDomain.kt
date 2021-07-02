package com.example.doctorappointment.domain.model

import com.example.doctorappointment.common.BindableItem

data class ReviewDomain(
    val patientName: String,
    val review: String,
    val vote: String
) : BindableItem
