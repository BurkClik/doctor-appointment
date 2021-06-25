package com.example.doctorappointment.ui.doctor.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.doctorappointment.adapter.DoctorAdapter
import com.example.doctorappointment.databinding.FragmentDoctorListBinding
import com.example.doctorappointment.ui.home.DoctorDecorator
import java.util.*

class DoctorListFragment : Fragment() {
    private var _binding: FragmentDoctorListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DoctorListViewModel by viewModels()

    private val doctorAdapter = DoctorAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoctorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewDoctors.apply {
            adapter = doctorAdapter
            addItemDecoration(DoctorDecorator())
        }

        binding.textInputSearchField.addTextChangedListener {
            viewModel.newSearch(
                binding.textInputSearch.editText!!.text.toString().toLowerCase(
                    Locale.getDefault()
                )
            )
        }

        viewModel.currentQuery.observe(viewLifecycleOwner) {
            doctorAdapter.submitList(it)
        }
    }
}