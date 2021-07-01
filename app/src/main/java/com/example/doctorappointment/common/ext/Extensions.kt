package com.example.doctorappointment.common.ext

import android.content.Context
import java.util.*


fun String.capitalizeEachWord(): String = split(" ")
    .joinToString(" ") {
        it.capitalize(Locale.getDefault())
    }

fun Int.resolveAsString(context: Context) = context.getString(this)