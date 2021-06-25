package com.example.doctorappointment.util

interface Validator {
    fun validate(field: String?): String?
}