package com.example.doctorappointment.common

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}