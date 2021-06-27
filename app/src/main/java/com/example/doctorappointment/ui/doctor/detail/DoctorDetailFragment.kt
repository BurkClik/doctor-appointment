package com.example.doctorappointment.ui.doctor.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.ext.capitalizeEachWord
import com.example.doctorappointment.databinding.FragmentDoctorDetailBinding
import com.example.doctorappointment.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorDetailFragment : BaseFragment() {

    override val viewModel: DoctorDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDoctorDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}