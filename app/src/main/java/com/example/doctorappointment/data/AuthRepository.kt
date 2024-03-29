package com.example.doctorappointment.data

import android.util.Log
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.data.remote.api.ApiService
import com.example.doctorappointment.data.remote.model.AppointmentRequest
import com.example.doctorappointment.data.remote.model.AppointmentResponse
import com.example.doctorappointment.data.remote.model.LoginRequest
import com.example.doctorappointment.data.remote.model.LoginResponse
import com.example.doctorappointment.data.remote.model.ReviewRequest
import com.example.doctorappointment.data.remote.model.SignUpRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {
    fun sendLoginRequest(
        email: String,
        password: String
    ): Flow<Resource<LoginResponse?>> = flow {
        val request = LoginRequest(email, password)

        val response = try {
            emit(Resource.Loading)
            apiService.login(request)
        } catch (ex: Exception) {
            null
        }

        emit(
            when (response?.code()) {
                200 -> Resource.Success(response.body())
                else -> Resource.Error(null)
            }
        )
    }

    fun sendSignUpRequest(
        name: String,
        mail: String,
        password: String
    ): Flow<Resource<LoginResponse?>> = flow {
        val request = SignUpRequest(name, mail, password)

        val response = try {
            emit(Resource.Loading)
            apiService.signUp(request)
        } catch (ex: Exception) {
            null
        }

        Log.i("Burak", "${response?.code()}")

        emit(
            when (response?.code()) {
                201 -> Resource.Success(response.body())
                else -> Resource.Error(null)
            }
        )
    }

    fun getAppointment(
        id: String,
        hour: String,
        date: String,
        patientName: String,
        doctorName: String
    ): Flow<Resource<AppointmentResponse?>> = flow {
        val request = AppointmentRequest(hour, date, patientName, doctorName)

        val response = try {
            emit(Resource.Loading)
            apiService.getAppointment(id, request)
        } catch (ex: Exception) {
            null
        }

        Log.i("Burak", "${response?.code()}")

        emit(
            when (response?.code()) {
                200 -> Resource.Success(response.body())
                else -> Resource.Error(null)
            }
        )
    }

    fun makeReview(
        id: String,
        patientName: String,
        review: String,
        vote: Float
    ): Flow<Resource<AppointmentResponse?>> = flow {
        val request = ReviewRequest(patientName, review, vote)

        val response = try {
            emit(Resource.Loading)
            apiService.review(id, request)
        } catch (ex: Exception) {
            null
        }

        emit(
            when (response?.code()) {
                200 -> Resource.Success(response.body())
                else -> Resource.Error(null)
            }
        )
    }
}