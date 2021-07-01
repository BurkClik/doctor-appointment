package com.example.doctorappointment.ui.register.validator

import androidx.annotation.StringRes
import com.example.doctorappointment.R
import com.example.doctorappointment.common.Validator

class EmailValidator : Validator {
    @StringRes
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.this_field_is_required
        !field.contains('@')
                || !field.split('@')[1].contains('.')
                || field.split('@')[0].length !in 6..50 -> R.string.email_is_invalid
        else -> null
    }
}