package com.example.doctorappointment.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.GenericAdapter
import com.example.doctorappointment.data.local.Category
import com.example.doctorappointment.data.local.JwtStore
import com.example.doctorappointment.databinding.FragmentHomeBinding
import com.example.doctorappointment.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    override val viewModel by viewModels<HomeViewModel>()

    // Category
    private val categoryAdapter = GenericAdapter<Category>(R.layout.item_category)

    // Doctor
    private val doctorAdapter = GenericAdapter<User>(R.layout.item_doctor)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("Burak", "${JwtStore(requireContext()).loadJwt()}")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Initialize category data
        binding.recyclerviewCard.apply {
            adapter = categoryAdapter
            addItemDecoration(CategoryDecorator())
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        // Initialize doctor data
        binding.recyclerviewDoctor.apply {
            adapter = doctorAdapter
            addItemDecoration(DoctorDecorator())
        }

        doctorAdapter.itemClickListener = viewModel.itemClickListener

        viewModel.remoteDoctors.observe(viewLifecycleOwner) {
            doctorAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}