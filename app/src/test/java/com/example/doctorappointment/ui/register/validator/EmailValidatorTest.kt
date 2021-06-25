package com.example.doctorappointment.ui.register.validator

import org.junit.Assert.*

import junit.framework.TestCase
import org.junit.Test

class EmailValidatorTest() {
    private val emailValidator = EmailValidator()

    @Test
    fun `should return false given empty email`() {
        // Given
        val email = ""

        // When
        val actualResult = emailValidator.isEmailEmpty(email)

        // Then
        assertFalse(actualResult)
    }

    @Test
    fun `should return false given not contains at sign or dot email`() {
        // Given
        val email = "test@gmailcom"

        // When
        val actualResult = emailValidator.containsAtAndDot(email)

        // Then
        assertFalse(actualResult)
    }
}