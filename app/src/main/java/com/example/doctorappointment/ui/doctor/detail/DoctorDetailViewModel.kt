package com.example.doctorappointment.ui.doctor.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.domain.UserUseCase
import com.example.doctorappointment.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userUseCase: UserUseCase,
) :
    BaseViewModel() {

    private var _doctor = MutableLiveData<User?>()
    val doctor: LiveData<User?> = _doctor

    private val doctorId: String = savedStateHandle["doctorId"]!!
    private val doctorName: String = savedStateHandle["doctorName"]!!

    init {
        Log.i("Burak", "Detail -> $doctorId")
        fetchDoctorDetail()
    }

    private fun fetchDoctorDetail() = viewModelScope.launch {
        userUseCase.getDoctorDetail(doctorId).collect { resource ->
            when (resource) {
                is Resource.Success -> _doctor.value = resource.data
                is Resource.Error -> Log.i("Burak", resource.exception?.message.toString())
                is Resource.Loading -> Log.i("Burak", "Loading")
            }
        }
    }

    fun appointment() {
        val action = DoctorDetailFragmentDirections.actionDoctorDetailFragmentToAppointmentFragment(doctorId, doctorName)
        navigation.navigate(action)
    }
}