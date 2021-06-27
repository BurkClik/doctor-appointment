package com.example.doctorappointment.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun setImage(view: ImageView, @DrawableRes image: Int) {
    Glide.with(view)
        .load(image)
        .into(view)
}