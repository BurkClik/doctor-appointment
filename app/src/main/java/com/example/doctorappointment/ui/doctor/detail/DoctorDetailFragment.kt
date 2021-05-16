package com.example.doctorappointment.ui.doctor.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.doctorappointment.common.ext.capitalizeEachWord
import com.example.doctorappointment.databinding.FragmentDoctorDetailBinding

class DoctorDetailFragment : Fragment() {
    private var _binding: FragmentDoctorDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DoctorDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoctorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doctorName = args.doctorName
        val doctorTitle = args.doctorTitle
        val doctorDepartment = args.doctorDepartment
        val doctorAbout = args.doctorAbout
        val doctorImage = args.doctorImage

        binding.apply {
            textViewDoctorName.text = doctorName.capitalizeEachWord()
            textViewDoctorTitle.text = doctorTitle
            textViewDoctorDepartment.text = doctorDepartment
            textViewDoctorAbout.text = doctorAbout
            imageViewProfileImage.setImageResource(doctorImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}