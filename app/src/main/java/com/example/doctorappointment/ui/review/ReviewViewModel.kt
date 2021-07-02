package com.example.doctorappointment.ui.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.AuthRepository
import com.example.doctorappointment.data.local.JwtStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val authRepository: AuthRepository,
    private val jwtStore: JwtStore
) : BaseViewModel() {

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData(true)
    val loadingState: LiveData<Boolean> = _loadingState

    private val _successLiveData: MutableLiveData<String> = MutableLiveData()
    val successLiveData: LiveData<String> = _successLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    val doctorReview = MutableLiveData<String>()
    val doctorVote = MutableLiveData<Float>()

    val doctorName: String = savedStateHandle["doctorName"]!!
    val doctorId: String = savedStateHandle["doctorId"]!!

    private fun toDoctor() {
        val action = ReviewFragmentDirections.actionReviewFragmentToHomeFragment()
        navigation.navigate(action)
    }

    fun makeReview() {
        viewModelScope.launch {
            authRepository.makeReview(
                id = doctorId,
                patientName = jwtStore.loadName().toString(),
                review = doctorReview.value!!,
                vote = doctorVote.value!!
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _loadingState.value = false
                        _successLiveData.value = "Yorumunuz başarılı bir şekilde kaydedildi!"
                        toDoctor()
                    }
                    is Resource.Error -> {
                        _loadingState.value = false
                        _errorLiveData.value = "Bir hata oluştu!"
                    }
                    Resource.Loading -> _loadingState.value = true
                }
            }
        }

        viewModelScope.launch {
            authRepository.makeReview(
                id = jwtStore.loadId().toString(),
                patientName = jwtStore.loadName().toString(),
                review = doctorReview.value!!,
                vote = doctorVote.value!!
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> Log.i("Burak", "Başarılı")
                    is Resource.Error -> Log.i("Burak", "Yine yangınlar yine ben")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
        }
    }
}