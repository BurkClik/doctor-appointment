package com.example.doctorappointment.ui.appointment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorappointment.BR
import com.example.doctorappointment.R
import com.example.doctorappointment.databinding.ItemAppointmentHourBinding
import com.example.doctorappointment.domain.model.Appointment

class AppointmentAdapter : ListAdapter<Appointment, AppointmentAdapter.AppointmentViewHolder>(
    DIFF_UTIL
) {

    var itemClickListener: (Appointment) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val binding = DataBindingUtil.inflate<ItemAppointmentHourBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_appointment_hour,
            parent,
            false
        )
        return AppointmentViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AppointmentViewHolder(
        private val binding: ItemAppointmentHourBinding,
        private val itemClickListener: (Appointment) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(appointment: Appointment) {
            binding.setVariable(BR.baseModel, appointment)
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                itemClickListener
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Appointment>() {
            override fun areItemsTheSame(oldItem: Appointment, newItem: Appointment) =
                oldItem.appointmentHour == newItem.appointmentHour

            override fun areContentsTheSame(oldItem: Appointment, newItem: Appointment) =
                oldItem == newItem
        }
    }
}