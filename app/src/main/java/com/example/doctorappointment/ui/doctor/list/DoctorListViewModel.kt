package com.example.doctorappointment.ui.doctor.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.domain.UserUseCase
import com.example.doctorappointment.domain.model.User
import com.example.doctorappointment.ui.home.HomeFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DoctorListViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {
    private val _currentQuery = MutableLiveData<List<User>?>()
    private val _newQuery = MutableLiveData<String>()

    val currentQuery: LiveData<List<User>?> = _currentQuery
    val newQuery: LiveData<String> = _newQuery

    val itemClickListener: (User) -> Unit = {
        val action = DoctorListFragmentDirections.actionDoctorSearchFragmentToDoctorDetailFragment(it._id)
        navigation.navigate(action)
    }

    init {
        getSearchResult()
    }

    private fun getSearchResult(query: String = DEFAULT_QUERY) {
        viewModelScope.launch {
            userUseCase.getSearchDoctor(query).collect { resource ->
                when (resource) {
                    is Resource.Success -> _currentQuery.value = resource.data
                    is Resource.Error -> Log.i("Burak", "Hata")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
        }
    }

    fun newSearch(query: String) {
        _newQuery.value = query
        getSearchResult(newQuery.value!!)
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }
}