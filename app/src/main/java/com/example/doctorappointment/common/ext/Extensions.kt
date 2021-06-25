package com.example.doctorappointment.common.ext

import android.content.res.Resources
import com.example.doctorappointment.util.Validator
import com.google.android.material.textfield.TextInputLayout
import java.util.*


fun String.capitalizeEachWord(): String = split(" ")
    .joinToString(" ") {
        it.capitalize(Locale.getDefault())
    }