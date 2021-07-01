package com.example.doctorappointment.ui.doctor.department

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
class DepartmentViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _doctors = MutableLiveData<List<User>?>()
    val doctors: LiveData<List<User>?> = _doctors

    private val departmentName: String = savedStateHandle["departmentName"]!!

    val itemClickListener: (User) -> Unit = {
        val action = DepartmentFragmentDirections.actionDepartmentFragmentToDoctorDetailFragment(
            it._id,
            it.name
        )
        navigation.navigate(action)
    }

    init {
        getDoctorsByDepartment()
    }

    private fun getDoctorsByDepartment() = viewModelScope.launch {
        userUseCase.getSearchDoctor(doctorQuery = departmentName.lowercase())
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> _doctors.value = resource.data
                    is Resource.Error -> Log.i("Burak", "Yine yangınlar yine ben")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
    }
}