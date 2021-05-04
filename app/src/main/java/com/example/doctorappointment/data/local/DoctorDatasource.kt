package com.example.doctorappointment.data.local

import com.example.doctorappointment.R
import com.example.doctorappointment.data.local.Doctor

class DoctorDatasource {
    fun loadDoctorList(): List<Doctor> {
        return listOf<Doctor>(
            Doctor(
                R.drawable.doctor_image,
                "Dr. Kathryn Murphy",
                "Dentist",
                R.drawable.ic_category_mage,
                "4.6"
            ),
            Doctor(
                R.drawable.doctor_image,
                "Dr. Jenny Wilson",
                "Orthopedic",
                R.drawable.ic_orthopedic,
                "3.2"
            ),
            Doctor(
                R.drawable.doctor_image,
                "Dr. Zac Wolff",
                "Cardiology",
                R.drawable.ic_cardiology,
                "4.2"
            ),
            Doctor(
                R.drawable.doctor_image,
                "Dr. Stella Kane",
                "Neurology",
                R.drawable.ic_neurology,
                "4.7"
            ),
        )
    }
}