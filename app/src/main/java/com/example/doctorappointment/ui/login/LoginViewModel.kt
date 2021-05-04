package com.example.doctorappointment.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // State
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
}