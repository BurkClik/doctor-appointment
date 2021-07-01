package com.example.doctorappointment.ui.hospitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.GenericAdapter
import com.example.doctorappointment.databinding.FragmentHospitalBinding
import com.example.doctorappointment.domain.model.Hospital
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalFragment : BaseFragment() {

    override val viewModel: HospitalViewModel by viewModels()

    private val hospitalAdapter = GenericAdapter<Hospital>(R.layout.item_hospital)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHospitalBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_hospital, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerviewHospital.apply {
            adapter = hospitalAdapter
        }

        viewModel.hospitals.observe(viewLifecycleOwner) {
            hospitalAdapter.submitList(it)
        }

        hospitalAdapter.itemClickListener = viewModel.itemClickListener

        return binding.root
    }
}