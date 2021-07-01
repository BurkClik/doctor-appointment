package com.example.doctorappointment.ui.hospitals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.GenericAdapter
import com.example.doctorappointment.databinding.FragmentHospitalDoctorsBinding
import com.example.doctorappointment.domain.model.User
import com.example.doctorappointment.ui.home.DoctorDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalDoctorsFragment : BaseFragment() {

    override val viewModel: HospitalDoctorsViewModel by viewModels()

    private val doctorAdapter = GenericAdapter<User>(R.layout.item_doctor)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHospitalDoctorsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hospital_doctors, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerviewDoctors.apply {
            adapter = doctorAdapter
            addItemDecoration(DoctorDecorator())
        }

        Log.i("Burak", "Doctors -> ${viewModel.doctors.value}")

        viewModel.doctors.observe(viewLifecycleOwner) {
            doctorAdapter.submitList(it)
        }

        doctorAdapter.itemClickListener = viewModel.itemClickListener

        return binding.root
    }
}