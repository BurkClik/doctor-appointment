package com.example.doctorappointment.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.local.JwtStore
import com.example.doctorappointment.data.remote.model.AppointmentDemo
import com.example.doctorappointment.data.remote.model.AppointmentResponse
import com.example.doctorappointment.domain.UserUseCase
import com.example.doctorappointment.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val jwtStore: JwtStore,
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _user = MutableLiveData<AppointmentResponse?>()
    val user: LiveData<AppointmentResponse?> = _user

    private val _appointments = MutableLiveData<List<AppointmentDemo>>()
    val appointment: LiveData<List<AppointmentDemo>> = _appointments

    init {
        getUser()
    }

    fun logOut() {
        val action = ProfileFragmentDirections.logoutSuccess()
        jwtStore.apply {
            deleteJwt()
            deleteMail()
            deleteName()
            deleteId()
        }
        navigation.navigate(action)
    }

    private fun getUser() = viewModelScope.launch {
        val userId = jwtStore.loadId().toString()
        userUseCase.getProfile(userId).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    Log.i("Burak", "Success")
                    Log.i("Burak", "name -> ${jwtStore.loadId()}")
                    _user.value = resource.data
                    Log.i("Burak", "${user.value!!.appointment}")
                    _appointments.value = user.value!!.appointment
                }
                is Resource.Error -> Log.i("Burak", "${resource.exception?.message}")
                is Resource.Loading -> Log.i("Burak", "Loading")
            }
        }
    }
}