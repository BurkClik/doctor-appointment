package com.example.doctorappointment.data.local

import com.example.doctorappointment.R
import com.example.doctorappointment.data.local.Category

class CategoryDatasource {
    fun loadCategoryList(): List<Category> {
        return listOf<Category>(
            Category(R.drawable.ic_category_mage, R.string.dentist),
            Category(R.drawable.ic_neurology, R.string.neurology),
            Category(R.drawable.ic_cardiology, R.string.cardiology),
            Category(R.drawable.ic_orthopedic, R.string.orthopedic),
            Category(R.drawable.ic_category_mage, R.string.dentist),
            Category(R.drawable.ic_neurology, R.string.neurology),
            Category(R.drawable.ic_cardiology, R.string.cardiology),
            Category(R.drawable.ic_orthopedic, R.string.orthopedic)
        )
    }
}