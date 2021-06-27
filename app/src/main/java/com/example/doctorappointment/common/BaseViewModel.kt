package com.example.doctorappointment.common

import androidx.lifecycle.ViewModel
import com.example.doctorappointment.common.navigation.Navigation

abstract class BaseViewModel : ViewModel() {
    val navigation = Navigation()
}