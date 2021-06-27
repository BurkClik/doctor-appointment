package com.example.doctorappointment.data.local

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.doctorappointment.common.BindableItem

data class Category(@DrawableRes val categoryImage: Int, val categoryTitle: String) : BindableItem
