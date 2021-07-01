package com.example.doctorappointment.ui.appointment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.doctorappointment.R
import com.example.doctorappointment.common.BaseFragment
import com.example.doctorappointment.common.BaseViewModel
import com.example.doctorappointment.common.GenericAdapter
import com.example.doctorappointment.databinding.FragmentAppointmentBinding
import com.example.doctorappointment.domain.model.Appointment
import com.example.doctorappointment.ui.home.DoctorDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class AppointmentFragment : BaseFragment() {
    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!

    override val viewModel: AppointmentViewModel by viewModels()

    private val calender = Calendar.getInstance()

    private val appointmentHourAdapter = GenericAdapter<Appointment>(R.layout.item_appointment_hour)

    private fun loadAppointmentHour(): List<Appointment> {
        return listOf(
            Appointment("09:00", true),
            Appointment("09:30", true),
            Appointment("10:00", true),
            Appointment("10:30", true),
            Appointment("11:00", true),
            Appointment("11:30", true),
            Appointment("12:00", true),
            Appointment("12:30", true),
            Appointment("13:00", true),
            Appointment("13:30", true),
            Appointment("14:00", true),
            Appointment("14:30", true),
            Appointment("15:00", true),
            Appointment("15:30", true),
            Appointment("16:00", true),
            Appointment("16:30", true),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateF = DateFormat.getDateInstance(DateFormat.MEDIUM)
        viewModel.appointmentDate.value = dateF.format(Date())

        binding.calendarView.apply {
            setOnDateChangeListener { view, year, month, dayOfMonth ->
/*                val dateD = calender.time.time
                minDate = dateD*/

                calender.set(year, month, dayOfMonth)
                date = calender.timeInMillis


                val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)

                viewModel.appointmentDate.value = dateFormatter.format(calender.time)

                val sdf = SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault())
                val netDate = Date(date)
                val demo = sdf.format(netDate)

                Log.i("Burak", dateFormatter.format(calender.time))
                Log.i("Burak", dateFormatter.format(date))
                Log.i("Burak", dateFormatter.format(Date()))
            }
        }

        binding.recyclerviewAppointmentHour.apply {
            adapter = appointmentHourAdapter
            addItemDecoration(DoctorDecorator())
        }
        appointmentHourAdapter.submitList(loadAppointmentHour())

        appointmentHourAdapter.itemClickListener = viewModel.itemClickListener

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