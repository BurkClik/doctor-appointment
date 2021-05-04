package com.example.doctorappointment.data.local

import com.example.doctorappointment.R
import com.example.doctorappointment.data.local.Category

class CategoryDatasource {
    fun loadCategoryList(): List<Category> {
        return listOf<Category>(
            Category(R.drawable.ic_category_mage, "Dentist"),
            Category(R.drawable.ic_neurology, "Neurology"),
            Category(R.drawable.ic_cardiology, "Cardiology"),
            Category(R.drawable.ic_orthopedic, "Orthopedic"),
            Category(R.drawable.ic_category_mage, "Dentist"),
            Category(R.drawable.ic_neurology, "Neurology"),
            Category(R.drawable.ic_cardiology, "Cardiology"),
            Category(R.drawable.ic_orthopedic, "Orthopedic")
        )
    }
}