package com.example.doctorappointment.data.local

import com.example.doctorappointment.R
import com.example.doctorappointment.data.local.Category

class CategoryDatasource {
    fun loadCategoryList(): List<Category> {
        return listOf<Category>(
            Category(R.drawable.ic_category_mage, "Dişçi"),
            Category(R.drawable.ic_neurology, "Nöroloji"),
            Category(R.drawable.ic_cardiology, "Kardiyoloji"),
            Category(R.drawable.ic_orthopedic, "Ortopedi"),
            Category(R.drawable.ic_neurology, "Dahiliye"),
            Category(R.drawable.ic_cardiology, "Üroloji"),
            Category(R.drawable.ic_neurology, "Cildiye"),
            Category(R.drawable.ic_neurology, "Genel Cerrahi"),
        )
    }
}