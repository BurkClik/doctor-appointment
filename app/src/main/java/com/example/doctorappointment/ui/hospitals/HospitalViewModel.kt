package com.example.doctorappointment.ui.hospitals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.domain.HospitalUseCase
import com.example.doctorappointment.domain.model.Hospital
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalViewModel @Inject constructor(private val hospitalUseCase: HospitalUseCase) :
    BaseViewModel() {

    private val _hospitals = MutableLiveData<List<Hospital>?>()
    val hospitals: LiveData<List<Hospital>?> = _hospitals

    val itemClickListener: (Hospital) -> Unit = {
        val action = HospitalFragmentDirections.actionHospitalFragment2ToHospitalDoctorsFragment(
            it.name.lowercase().replace(" ", "&")
        )
        navigation.navigate(action)
    }

    init {
        getHospitals()
    }

    private fun getHospitals() = viewModelScope.launch {
        hospitalUseCase.getHospital()
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> _hospitals.value = resource.data
                    is Resource.Error -> Log.i("Burak", "Hata")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
    }
}