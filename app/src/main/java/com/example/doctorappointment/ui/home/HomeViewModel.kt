package com.example.doctorappointment.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.local.Category
import com.example.doctorappointment.data.local.CategoryDatasource
import com.example.doctorappointment.domain.UserUseCase
import com.example.doctorappointment.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    private val _remoteDoctors = MutableLiveData<List<User>?>()

    val categories: LiveData<List<Category>> = _categories
    val remoteDoctors: LiveData<List<User>?> = _remoteDoctors

    val itemClickListener: (User) -> Unit = {
        val action = HomeFragmentDirections.actionHomeFragmentToDoctorDetailFragment(doctorId = it._id, it.name)
        navigation.navigate(action)
    }

    val itemClickListenerCategory: (Category) -> Unit = {
        val action = HomeFragmentDirections.actionHomeFragmentToDepartmentFragment(it.categoryTitle)
        navigation.navigate(action)
    }

    init {
        listOfCategories()
        fetchTopRatedDoctor()
        Log.i("Burak", "${_remoteDoctors.value}")
    }

    private fun fetchTopRatedDoctor() {
        viewModelScope.launch {
            userUseCase.getTopRatedDoctor(5).collect { resource ->
                when (resource) {
                    is Resource.Success -> _remoteDoctors.value = resource.data
                    is Resource.Error -> Log.i("Burak", "Hata")
                    is Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
        }
    }

    private fun listOfCategories(): List<Category> {
        _categories.value = CategoryDatasource().loadCategoryList()
        return _categories.value!!
    }
}