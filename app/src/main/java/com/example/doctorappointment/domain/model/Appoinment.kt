package com.example.doctorappointment.domain.model

import com.example.doctorappointment.common.BindableItem

data class Appointment(val appointmentHour: String, var state: Boolean) : BindableItem