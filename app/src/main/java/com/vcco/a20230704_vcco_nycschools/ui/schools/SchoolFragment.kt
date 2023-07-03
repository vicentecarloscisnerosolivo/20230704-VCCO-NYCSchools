package com.vcco.a20230704_vcco_nycschools.ui.schools

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vcco.a20230704_vcco_nycschools.R

class SchoolFragment : Fragment() {

    companion object {
        fun newInstance() = SchoolFragment()
    }

    private lateinit var viewModel: SchoolViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schools, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SchoolViewModel::class.java)
        // TODO: Use the ViewModel
    }

}