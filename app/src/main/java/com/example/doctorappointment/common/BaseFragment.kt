package com.example.doctorappointment.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.doctorappointment.common.navigation.NavigationObserver
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel

    private val navigationObserver = NavigationObserver()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationObserver.observer(viewModel.navigation, findNavController(), viewLifecycleOwner)
    }

    fun showSnack(message: String, view: View) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .show()
    }
}