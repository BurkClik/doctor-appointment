package com.example.doctorappointment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctorappointment.data.local.Category
import com.example.doctorappointment.data.local.CategoryDatasource
import com.example.doctorappointment.data.local.Doctor
import com.example.doctorappointment.data.local.DoctorDatasource

class HomeViewModel : ViewModel() {
    private val _doctors = MutableLiveData<List<Doctor>>()
    private val _categories = MutableLiveData<List<Category>>()

    val doctors: LiveData<List<Doctor>> = _doctors
    val categories: LiveData<List<Category>> = _categories


    init {
        listOfDoctors()
        listOfCategories()
    }

    private fun listOfDoctors() : List<Doctor> {
        _doctors.value = DoctorDatasource().loadDoctorList()
        return _doctors.value!!
    }

    private fun listOfCategories() : List<Category> {
        _categories.value = CategoryDatasource().loadCategoryList()
        return _categories.value!!
    }
}