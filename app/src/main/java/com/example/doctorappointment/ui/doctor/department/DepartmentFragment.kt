package com.example.doctorappointment.ui.doctor.department

import android.os.Bundle
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
import com.example.doctorappointment.databinding.FragmentDepartmentBinding
import com.example.doctorappointment.domain.model.User
import com.example.doctorappointment.ui.home.DoctorDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DepartmentFragment : BaseFragment() {

    override val viewModel: DepartmentViewModel by viewModels()

    private val doctorAdapter = GenericAdapter<User>(R.layout.item_doctor)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentDepartmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_department, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerviewDoctors.apply {
            adapter = doctorAdapter
            addItemDecoration(DoctorDecorator())
        }

        viewModel.doctors.observe(viewLifecycleOwner) {
            doctorAdapter.submitList(it)
        }

        doctorAdapter.itemClickListener = viewModel.itemClickListener

        return binding.root
    }
}