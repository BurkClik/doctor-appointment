package com.example.doctorappointment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorappointment.R
import com.example.doctorappointment.data.local.Doctor
import com.example.doctorappointment.databinding.ItemDoctorBinding

class DoctorAdapter : ListAdapter<Doctor, DoctorAdapter.DoctorViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DoctorViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(doctor: Doctor) {
                binding.doctorName.text = doctor.doctorName
                binding.doctorCategory.text = doctor.doctorCategory
                binding.ratingText.text = doctor.doctorRating
                binding.doctorImage.setImageResource(R.drawable.doctor_image)
                binding.doctorCategoryIcon.setImageResource(R.drawable.ic_star_rate)
            }
    }

    companion object {
        var DIFF_CALLBACK = object : DiffUtil.ItemCallback<Doctor>() {
            override fun areItemsTheSame(oldItem: Doctor, newItem: Doctor) =
                oldItem.doctorName == newItem.doctorName

            override fun areContentsTheSame(oldItem: Doctor, newItem: Doctor) = oldItem == newItem
        }
    }
}