package com.example.doctorappointment.domain.model

import com.example.doctorappointment.common.BindableItem

data class Hospital(val id: String, val name: String, val city: String) : BindableItem