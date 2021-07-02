package com.example.doctorappointment.ui.review

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.databinding.FragmentReviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!

    override val viewModel: ReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ratingBarDoctor.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            viewModel.doctorVote.value = rating
        }


        with(viewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                showSnack(it, view)
            }

            successLiveData.observe(viewLifecycleOwner) {
                showSnack(it, view)
            }
        }
    }

}