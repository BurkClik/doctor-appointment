package com.example.doctorappointment.domain

import android.util.Log
import com.example.doctorappointment.common.Resource
import com.example.doctorappointment.common.map
import com.example.doctorappointment.data.UserRepository
import com.example.doctorappointment.domain.mapper.DoctorDetailMapper
import com.example.doctorappointment.domain.mapper.UserMapper
import com.example.doctorappointment.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val doctorDetailMapper: DoctorDetailMapper,
    private val userMapper: UserMapper,
    private val userRepository: UserRepository
) {
    fun getTopRatedDoctor(count: Int): Flow<Resource<List<User>>> = userRepository
        .getTopRatedDoctor(count)
        .map {
            it.map { userResponse ->
                userMapper.mapFrom(userResponse)
            }
        }
        .onStart { Resource.Loading }
        .catch { Resource.Error(it) }
        .flowOn(Dispatchers.Default)

    fun getSearchDoctor(doctorQuery: String): Flow<Resource<List<User>>> = userRepository
        .getSearchDoctor(doctorQuery)
        .map {
            it.map { userResponse ->
                userMapper.mapFrom(userResponse)
            }
        }
        .onStart { Resource.Loading }
        .catch { Resource.Error(it) }
        .flowOn(Dispatchers.Default)

    fun getUser(user: String): Flow<Resource<List<User>>> = userRepository
        .getUser(user)
        .map {
            it.map { userResponse ->
                Log.i("Burak", "Success Flow")
                userMapper.mapFrom(userResponse)
            }
        }
        .onStart { Resource.Loading }
        .catch {
            Log.i("Burak", "Error")
            Resource.Error(it)
        }
        .flowOn(Dispatchers.Default)

    fun getDoctorDetail(id: String): Flow<Resource<User>> = userRepository
        .getDoctorDetail(id)
        .map {
            it.map { userResponse ->
                doctorDetailMapper.mapFrom(userResponse)
            }
        }
        .onStart { Resource.Loading }
        .catch { Resource.Error(it) }
        .flowOn(Dispatchers.Default)
}