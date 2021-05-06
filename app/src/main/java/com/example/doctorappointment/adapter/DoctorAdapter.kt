package com.example.doctorappointment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorappointment.R
import com.example.doctorappointment.data.remote.User
import com.example.doctorappointment.databinding.ItemDoctorBinding
import java.util.*

class DoctorAdapter : ListAdapter<User, DoctorAdapter.DoctorViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: (User) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DoctorViewHolder(private val binding: ItemDoctorBinding, private val itemClickListener: (User) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.doctorName.text = user.name
            binding.doctorCategory.text = user.doctor.department.capitalize(Locale.getDefault())
            binding.doctorImage.setImageResource(R.drawable.doctor_image)

            binding.root.setOnClickListener {
                itemClickListener(user)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
        }
    }
}