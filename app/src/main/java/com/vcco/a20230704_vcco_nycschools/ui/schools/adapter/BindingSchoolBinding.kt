package com.vcco.a20230704_vcco_nycschools.ui.schools.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.vcco.a20230704_vcco_nycschools.model.School

/**
 * Binding school name into TextView
 * @param item School
 */
@BindingAdapter("schoolName")
fun TextView.setSchoolName(item: School?) {
    item?.let {
        text = it.schoolName
    }
}