package com.example.doctorappointment.ui.register.validator

import androidx.annotation.StringRes
import com.example.doctorappointment.R
import com.example.doctorappointment.common.Validator

class UsernameValidator : Validator {
    @StringRes
    override fun validate(field: String)= when {
        field.isEmpty() -> R.string.username_is_required
        field.length < 3 -> R.string.username_is_too_short
        field.length > 20 -> R.string.username_is_too_long
        !field.replace('_', 'a').all { it.isLetterOrDigit() } -> R.string.username_not_consist
        else -> null
    }
}