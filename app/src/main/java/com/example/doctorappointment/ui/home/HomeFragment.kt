package com.example.doctorappointment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.doctorappointment.adapter.CategoryAdapter
import com.example.doctorappointment.adapter.DoctorAdapter
import com.example.doctorappointment.data.local.CategoryDatasource
import com.example.doctorappointment.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel by viewModels<HomeViewModel>()

    // Category
    private val categoryDatasource = CategoryDatasource().loadCategoryList()
    private val categoryAdapter = CategoryAdapter()

    // Doctor
    private val doctorAdapter = DoctorAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        viewModel.remoteDoctors.observe(viewLifecycleOwner) {
            doctorAdapter.submitList(it)
        }
    }
}