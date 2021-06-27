package com.example.doctorappointment.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.local.JwtStore
import com.example.doctorappointment.domain.UserUseCase
import com.example.doctorappointment.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val jwtStore: JwtStore,
    private val userUseCase: UserUseCase,
) : BaseViewModel() {

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        getUser()
    }

    fun logOut() {
        val action = ProfileFragmentDirections.logoutSuccess()
        jwtStore.apply {
            deleteJwt()
            deleteMail()
        }
        navigation.navigate(action)
    }

    private fun getUser() = viewModelScope.launch {
        userUseCase.getUser(jwtStore.loadMail().toString()).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    Log.i("Burak", "Success")
                    Log.i("Burak", "name -> ${user.value?.name}")
                    _user.value = resource.data[0]
                }
                is Resource.Error -> Log.i("Burak", "${resource.exception?.message}")
                is Resource.Loading -> Log.i("Burak", "Loading")
            }
        }
    }
}