package com.example.doctorappointment.ui.doctor.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.GenericAdapter
import com.example.doctorappointment.databinding.FragmentDoctorListBinding
import com.example.doctorappointment.domain.model.User
import com.example.doctorappointment.ui.home.DoctorDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DoctorListFragment : BaseFragment() {
    private var _binding: FragmentDoctorListBinding? = null
    private val binding get() = _binding!!

    override val viewModel: DoctorListViewModel by viewModels()

    private val doctorAdapter = GenericAdapter<User>(R.layout.item_doctor)



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

        doctorAdapter.itemClickListener = viewModel.itemClickListener

        viewModel.currentQuery.observe(viewLifecycleOwner) {
            doctorAdapter.submitList(it)
        }
    }
}