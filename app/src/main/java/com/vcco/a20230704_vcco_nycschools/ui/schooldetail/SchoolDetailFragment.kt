package com.vcco.a20230704_vcco_nycschools.ui.schooldetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vcco.a20230704_vcco_nycschools.R

class SchoolDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SchoolDetailFragment()
    }

    private lateinit var viewModel: SchoolDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_school_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SchoolDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}