package com.example.doctorappointment.domain.mapper

import com.example.doctorappointment.common.Mapper
import com.example.doctorappointment.data.remote.model.Review
import com.example.doctorappointment.domain.model.ReviewDomain
import javax.inject.Inject

class ReviewMapper @Inject constructor() : Mapper<List<Review>, List<ReviewDomain>> {
    override fun mapFrom(type: List<Review>): List<ReviewDomain> {
        return type.map {
            ReviewDomain(
                patientName = it.patientName,
                review = it.review,
                vote = it.vote.toString()
            )
        }
    }
}