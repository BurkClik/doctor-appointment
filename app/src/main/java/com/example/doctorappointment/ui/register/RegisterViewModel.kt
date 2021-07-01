package com.example.doctorappointment.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.AuthRepository
import com.example.doctorappointment.data.local.JwtStore
import com.example.doctorappointment.ui.register.validator.EmailValidator
import com.example.doctorappointment.ui.register.validator.PasswordValidator
import com.example.doctorappointment.ui.register.validator.UsernameValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val jwtStore: JwtStore
) : BaseViewModel() {

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val usernameValidator = UsernameValidator()

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _emailErrorMessage = MutableLiveData<Int>()
    private val _passwordErrorMessage = MutableLiveData<Int>()
    private val _usernameErrorMessage = MutableLiveData<Int>()

    val emailErrorMessage: LiveData<Int> = _emailErrorMessage
    val passwordErrorMessage: LiveData<Int> = _passwordErrorMessage
    val usernameErrorMessage: LiveData<Int> = _usernameErrorMessage

    private val _buttonEnableState = MutableLiveData(true)
    val buttonEnableState: LiveData<Boolean> = _buttonEnableState

    fun onClickButton() = viewModelScope.launch {
        if (isEmailValid() and isUsernameValid() and isPasswordValid()) {
            authRepository.sendSignUpRequest(
                name.value.orEmpty(),
                email.value.orEmpty(),
                password.value.orEmpty()
            )
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            Log.i("Burak", "Success")
                            val directions = RegisterFragmentDirections.sginUpSuccess()
                            navigation.navigate(directions)
                            _buttonEnableState.value = true
                            jwtStore.saveJwt(resource.data!!.token)
                        }
                        is Resource.Error -> Log.i("Burak", "Hata")
                        Resource.Loading -> Log.i("Burak", "Loading")
                    }
                }
        }
    }

    private fun isEmailValid(): Boolean {
        _emailErrorMessage.value = emailValidator.validate(email.value.orEmpty())
        return _emailErrorMessage.value == null
    }

    private fun isPasswordValid(): Boolean {
        _passwordErrorMessage.value = passwordValidator.validate(password.value.orEmpty())
        return _passwordErrorMessage.value == null
    }

    private fun isUsernameValid(): Boolean {
        _usernameErrorMessage.value = usernameValidator.validate(name.value.orEmpty())
        return _usernameErrorMessage.value == null
    }
}