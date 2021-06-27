package com.example.doctorappointment.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.AuthRepository
import com.example.doctorappointment.data.UserRepository
import com.example.doctorappointment.data.local.JwtStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: AuthRepository,
    private val jwtStore: JwtStore
) :
    BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _buttonEnableState = MutableLiveData(true)
    val buttonEnableState: LiveData<Boolean> = _buttonEnableState

    fun onClickButtonClick() = viewModelScope.launch {
        loginRepository.sendLoginRequest(email.value.orEmpty(), password.value.orEmpty())
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        Log.i("Burak", "Success")
                        val directions = LoginFragmentDirections.loginSuccess()
                        navigation.navigate(directions)
                        _buttonEnableState.value = true
                        jwtStore.saveJwt(resource.data!!.token)
                        Log.i("Burak", "Login -> ${resource.data.mail}")
                        jwtStore.saveMail(resource.data.mail)
                    }
                    is Resource.Error -> Log.i("Burak", "Hata")
                    is Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
    }

    fun toSignUp() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        navigation.navigate(action)
    }
}