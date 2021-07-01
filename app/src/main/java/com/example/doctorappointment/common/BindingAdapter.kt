package com.example.doctorappointment.common

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.doctorappointment.R
import com.example.doctorappointment.common.ext.resolveAsString
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setImage")
fun setImage(view: ImageView, @DrawableRes image: Int) {
    Glide.with(view)
        .load(image)
        .into(view)
}

@BindingAdapter("setColor")
fun setColor(view: TextView, state: Boolean) {
    if (state) view.setBackgroundResource(R.color.primary_color) else view.setBackgroundResource(R.color.unfocused_color)
}

@BindingAdapter("app:error")
fun TextInputLayout.error(@StringRes errorMessage: Int?) {
    error = errorMessage?.resolveAsString(context)
    isErrorEnabled = errorMessage != null
}