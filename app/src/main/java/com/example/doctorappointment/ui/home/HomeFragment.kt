package com.example.doctorappointment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorappointment.R
import com.example.doctorappointment.adapter.CategoryAdapter
import com.example.doctorappointment.adapter.DoctorAdapter
import com.example.doctorappointment.data.local.CategoryDatasource
import com.example.doctorappointment.data.local.DoctorDatasource

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize category data
        val categoryData = CategoryDatasource().loadCategoryList()

        val categoryRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_card)
        categoryRecyclerView.adapter = CategoryAdapter(view.context, categoryData)

        categoryRecyclerView.setHasFixedSize(true)

        // Initialize doctor data
        val doctorData = DoctorDatasource().loadDoctorList()

        val doctorRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_doctor)
        doctorRecyclerView.adapter = DoctorAdapter(view.context, doctorData)

        doctorRecyclerView.setHasFixedSize(true)
    }
}