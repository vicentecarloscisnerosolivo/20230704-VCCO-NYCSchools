package com.vcco.a20230704_vcco_nycschools.ui.schooldetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.vcco.a20230704_vcco_nycschools.R

class SchoolDetailFragment : Fragment() {

    private val viewModel: SchoolDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_detail, container, false)
    }
}