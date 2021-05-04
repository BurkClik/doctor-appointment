package com.example.doctorappointment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorappointment.R
import com.example.doctorappointment.data.local.Doctor

class DoctorAdapter(private val context: Context, private val dataset: List<Doctor>) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

        class DoctorViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val doctorImage: ImageView = view.findViewById(R.id.doctor_image)
            val doctorName: TextView = view.findViewById(R.id.doctor_name)
            val doctorCategory: TextView = view.findViewById(R.id.doctor_category)
            val doctorCategoryIcon: ImageView = view.findViewById(R.id.doctor_category_icon)
            val doctorRating: TextView = view.findViewById(R.id.rating_text)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val doctorAdapter = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctor_card, parent, false)
        return DoctorViewHolder(doctorAdapter)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = dataset[position]
        holder.doctorImage.setImageResource(doctor.doctorImage)
        holder.doctorName.text = doctor.doctorName
        holder.doctorCategory.text = context.resources.getString(doctor.doctorCategory)
        holder.doctorCategoryIcon.setImageResource(doctor.doctorCategoryIcon)
        holder.doctorRating.text = doctor.doctorRating
    }

    override fun getItemCount() = dataset.size
}