package com.example.doctorappointment.ui.doctor.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.doctorappointment.databinding.FragmentDoctorDetailBinding
import java.util.*

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
            textViewDoctorName.text = doctorName
            textViewDoctorTitle.text = doctorTitle
            textViewDoctorDepartment.text = doctorDepartment
            textViewDoctorAbout.text = doctorAbout
            imageViewProfileImage.setImageResource(doctorImage)
        }
    }

    // İsimleri bölüm ilk harflerini büyütüyor.
    private fun String.capitalizeSentence() : String {
        val words: MutableList<String> = this.split(" ").toMutableList()
        val word = ""
        for (i in words) {
            word.plus(i)
        }
        Log.i("Burak", "Word -> $word")
        return word
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}