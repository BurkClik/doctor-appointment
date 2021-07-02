package com.example.doctorappointment.ui.appointment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.AuthRepository
import com.example.doctorappointment.data.local.JwtStore
import com.example.doctorappointment.databinding.FragmentReviewBinding
import com.example.doctorappointment.domain.model.Appointment
import com.example.doctorappointment.ui.review.ReviewFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle,
    private val jwtStore: JwtStore,
) :
    BaseViewModel() {

    private val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val loadingState: LiveData<Boolean> = _loadingState

    private val _successLiveData: MutableLiveData<String> = MutableLiveData()
    val successLiveData: LiveData<String> = _successLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    val appointmentDate = MutableLiveData<String>()

    val _appointmentHour = MutableLiveData<String>()

    private val doctorId: String = savedStateHandle["doctorId"]!!
    private val doctorName: String = savedStateHandle["doctorName"]!!

    val itemClickListener: (Appointment) -> Unit = {
        Log.i("Burak", it.appointmentHour)

        _appointmentHour.value = it.appointmentHour
    }



    fun getAppointment() {
        val patientId = jwtStore.loadId().toString()
        val patientName = jwtStore.loadName().toString()
        Log.i("Burak", "doctor -> $doctorName\n patient $patientName")
        viewModelScope.launch {
            authRepository.getAppointment(
                doctorId,
                _appointmentHour.value.toString(),
                appointmentDate.value.toString(),
                patientName,
                doctorName
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _successLiveData.value = "Randevu alma başarılı!"
                        _loadingState.value = false
                        val action =
                            AppointmentFragmentDirections.actionAppointmentFragmentToHomeFragment()
                        navigation.navigate(action)
                    }
                    is Resource.Error -> {
                        _errorLiveData.value = "Bir hata oluştu!"
                        _loadingState.value = false
                    }
                    Resource.Loading -> _loadingState.value = true
                }
            }
        }

        viewModelScope.launch {
            authRepository.getAppointment(
                patientId,
                _appointmentHour.value.toString(),
                appointmentDate.value.toString(),
                patientName,
                doctorName
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> Log.i("Burak", "Başarıyla kaydedildi")
                    is Resource.Error -> Log.i("Burak", "Yine yangınlar yine ben")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
        }
    }
}