package com.example.doctorappointment.ui.profile

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
import com.example.doctorappointment.data.remote.model.AppointmentDemo
import com.example.doctorappointment.data.remote.model.Review
import com.example.doctorappointment.databinding.FragmentProfileBinding
import com.example.doctorappointment.domain.model.ReviewDomain
import com.example.doctorappointment.ui.home.DoctorDecorator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override val viewModel: ProfileViewModel by viewModels()

    private val appointmentAdapter = GenericAdapter<AppointmentDemo>(R.layout.item_appointment)
    private val reviewAdapter = GenericAdapter<ReviewDomain>(R.layout.item_review)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewUpcomingAppointments.apply {
            adapter = appointmentAdapter
        }

        binding.recyclerViewReview.apply {
            adapter = reviewAdapter
            addItemDecoration(DoctorDecorator())
        }

        viewModel.appointment.observe(viewLifecycleOwner) {
            appointmentAdapter.submitList(it.reversed())
        }

        viewModel.reviews.observe(viewLifecycleOwner) {
            reviewAdapter.submitList(it)
        }
    }
}