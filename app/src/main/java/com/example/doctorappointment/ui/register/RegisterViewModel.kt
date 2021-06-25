package com.example.doctorappointment.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctorappointment.ui.register.validator.EmailValidator
import com.example.doctorappointment.ui.register.validator.UsernameValidator

class RegisterViewModel : ViewModel() {
    // State
    private val _email = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    val email: LiveData<String> = _email

    var usernameError = MutableLiveData<String>()
    val emailError = MutableLiveData<String>()

    private val emailValidator = EmailValidator()
    private val usernameValidator = UsernameValidator()

    fun onSignUpButtonClick() {
        emailError.value = emailValidator.validate(email.value)
        usernameError.value = usernameValidator.validate(username.value)
        Log.i("Burak", username.value.toString())
        Log.i("Burak", "${usernameValidator.validate(username.value)}")
    }
}