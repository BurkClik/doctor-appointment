package com.example.doctorappointment.ui.register.validator

class EmailValidator {
    fun validate(field: String?) = when {
        field == null -> "Email cannot be empty"
        field.isBlank() -> "Email cannot be empty"
        field.length < 5 -> "Email cannot be this short"
        !field.contains("@")
                || !field.contains(".") -> "Invalid email"
        else -> null
    }

    fun isEmailNull(email: String?): Boolean = email == null

    /**
     * Given a [email] to this function
     * @return false if the email is empty
     * */
    fun isEmailEmpty(email: String?): Boolean = email!!.isNotEmpty() and email.isNotBlank()

    /**
     * Given a [email] to this function
     * @return false if it is not contains @ or .
     * */
    fun containsAtAndDot(email: String?): Boolean = email!!.contains('@') and email.contains('.')
}