package com.example.doctorappointment.ui.register.validator

import androidx.annotation.StringRes
import com.example.doctorappointment.R
import com.example.doctorappointment.common.Validator

class PasswordValidator : Validator {
    @StringRes
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.password_required
        field.length > 40 -> R.string.password_is_too_long
        field.length < 8 -> R.string.password_is_too_short
        field.all { it.isLetterOrDigit() }
                || !field.any { it.isDigit() }
                || !field.any { it.isUpperCase() }
                || !field.any { it.isLowerCase() } -> R.string.password_contain_check
        else -> null
    }
}