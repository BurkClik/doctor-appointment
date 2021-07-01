package com.example.doctorappointment.ui.login.validation

import com.example.doctorappointment.R
import com.example.doctorappointment.common.Validator

class IdentifierValidator : Validator {
    override fun validate(field: String) = when {
        field.isEmpty() -> R.string.this_field_is_required
        field.length < 5 -> R.string.identifier_is_too_short
        else -> null
    }
}