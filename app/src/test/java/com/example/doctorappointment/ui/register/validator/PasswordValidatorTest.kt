package com.example.doctorappointment.ui.register.validator

import com.example.doctorappointment.R
import com.google.common.truth.Truth
import org.junit.Assert.*

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PasswordValidatorTest(private val password: String, private val expected: Int?) {

    private val passwordValidator = PasswordValidator()

    @Test
    fun `password validation test`() {

        // When
        val actualResult = passwordValidator.validate(password)

        // Then
        Truth.assertThat(actualResult).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("", R.string.password_required),
            arrayOf("00000000000000000000000000000000000000000", R.string.password_is_too_long),
            arrayOf("2", R.string.password_is_too_short),
            arrayOf("burakcelik", R.string.password_contain_check)
        )
    }
}