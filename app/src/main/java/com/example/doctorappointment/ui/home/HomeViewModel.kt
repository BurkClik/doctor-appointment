package com.example.doctorappointment.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctorappointment.data.local.Category
import com.example.doctorappointment.data.local.CategoryDatasource
import com.example.doctorappointment.data.remote.ClientService
import com.example.doctorappointment.data.remote.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    private val _remoteDoctors = MutableLiveData<List<User>>()


    val categories: LiveData<List<Category>> = _categories
    val remoteDoctors: LiveData<List<User>> = _remoteDoctors

    init {
        listOfCategories()
        getDoctor()
    }

    private fun getDoctor() {
        ClientService.retrofitService.getDoctors()
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    Log.i("Burak", "$response")
                    Log.i("Burak", "${response.body()}")
                    if (response.isSuccessful and response.body()!!.isNotEmpty()) {
                        _remoteDoctors.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.e("Burak", t.message.toString())
                }
            })
    }

    private fun listOfCategories() : List<Category> {
        _categories.value = CategoryDatasource().loadCategoryList()
        return _categories.value!!
    }
}