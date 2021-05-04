package com.example.doctorappointment.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.doctorappointment.R

class DoctorDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = view.context.resources.getDimensionPixelSize(R.dimen.item_doctor_margin)
        outRect.bottom = spacing
    }
}