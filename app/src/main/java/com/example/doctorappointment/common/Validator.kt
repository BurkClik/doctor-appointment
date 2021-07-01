package com.example.doctorappointment.common

interface Validator {
    fun validate(field: String): Int?
}