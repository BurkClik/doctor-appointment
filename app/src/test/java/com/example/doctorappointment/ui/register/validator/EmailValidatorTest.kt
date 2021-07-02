package com.example.doctorappointment.ui.register.validator

import com.example.doctorappointment.R
import com.google.common.truth.Truth
import org.junit.Assert.*

import junit.framework.TestCase
import org.junit.Test

class EmailValidatorTest {

    private val emailValidator = EmailValidator()

    @Test
    fun `given empty email, when validate called, then should return this field is required` () {

        // Given
        val email = ""
        val expectedResult = R.string.this_field_is_required

        // When
        val actualResult = emailValidator.validate(email)

        // Then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `given not contains @ email, when validate called, then should return email is invalid` () {

        // Given
        val email = "burk.clik.com"
        val expectedResult = R.string.email_is_invalid

        // When
        val actualResult = emailValidator.validate(email)

        // Then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }
}