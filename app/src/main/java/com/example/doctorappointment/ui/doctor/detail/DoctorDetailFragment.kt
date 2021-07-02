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
import com.example.doctorappointment.common.GenericAdapter
import com.example.doctorappointment.common.ext.capitalizeEachWord
import com.example.doctorappointment.data.remote.model.Review
import com.example.doctorappointment.databinding.FragmentDoctorDetailBinding
import com.example.doctorappointment.databinding.FragmentRegisterBinding
import com.example.doctorappointment.domain.model.ReviewDomain
import com.example.doctorappointment.ui.home.DoctorDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorDetailFragment : BaseFragment() {

    private var _binding: FragmentDoctorDetailBinding? = null
    private val binding get() = _binding!!

    private val reviewAdapter = GenericAdapter<ReviewDomain>(R.layout.item_review)

    override val viewModel: DoctorDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewReview.apply {
            adapter = reviewAdapter
            addItemDecoration(DoctorDecorator())
        }

        viewModel.reviews.observe(viewLifecycleOwner) {
            reviewAdapter.submitList(it)
        }
    }
}