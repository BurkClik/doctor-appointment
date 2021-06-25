package com.example.doctorappointment.ui.doctor.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.doctorappointment.data.remote.ClientService
import com.example.doctorappointment.data.remote.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorListViewModel : ViewModel() {
    private val _currentQuery = MutableLiveData<List<User>>()
    private val _newQuery = MutableLiveData<String>()

    val currentQuery: LiveData<List<User>> = _currentQuery
    val newQuery: LiveData<String> = _newQuery

    init {
        getSearchResult()
    }

    private fun getSearchResult(query: String = DEFAULT_QUERY) {
        ClientService.retrofitService.searchDoctor(doctor = query)
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    Log.i("Burak", "$response")
                    if (response.isSuccessful and response.body()!!.isNotEmpty()) {
                        _currentQuery.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.e("Burak", t.message.toString())
                }
            })
    }

    fun newSearch(query: String) {
        _newQuery.value = query
        getSearchResult(newQuery.value!!)
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }
}