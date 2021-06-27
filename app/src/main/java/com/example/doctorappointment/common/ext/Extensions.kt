package com.example.doctorappointment.common.ext

import java.util.*


fun String.capitalizeEachWord(): String = split(" ")
    .joinToString(" ") {
        it.capitalize(Locale.getDefault())
    }