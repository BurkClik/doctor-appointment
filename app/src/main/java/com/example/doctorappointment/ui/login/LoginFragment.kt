package com.example.doctorappointment.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.doctorappointment.R
import com.example.doctorappointment.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var signUpButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentLoginBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpButton = view.findViewById(R.id.textButtonSignUp)
        signUpButton.setOnClickListener {
            it.findNavController().navigate(R.id.registerFragment)
        }
    }
}