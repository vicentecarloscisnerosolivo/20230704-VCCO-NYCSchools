package com.vcco.a20230704_vcco_nycschools.ui.schooldetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vcco.a20230704_vcco_nycschools.R
import com.vcco.a20230704_vcco_nycschools.databinding.FragmentSchoolDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private val viewModel: SchoolDetailViewModel by viewModels()
    private lateinit var binding: FragmentSchoolDetailBinding

    //create val to retrieve id from previous screen
    private val args: SchoolDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //request data from DB
        requestSchoolDetails()
        addObservers()
    }

    /**
     * Create instance of FragmentSchoolDetailBinding
     */
    private fun createBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_school_detail,
            container,
            false
        )
    }

    /**
     * Make the request in viewModel to retrieve the SchoolDetail and Score
     */
    private fun requestSchoolDetails() {
        val id = args.id
        viewModel.getSchoolDetail(id)
        viewModel.getSchoolScore(id)
    }

    /**
     * Observer the data from SchoolDetailViewModel
     */
    private fun addObservers() {
        //observer SchoolDetail
        viewModel.schoolDetail.observe(viewLifecycleOwner) { schoolDetail ->
            binding.schoolDetail = schoolDetail
        }
        //observer SchoolSATScores
        viewModel.schoolScore.observe(viewLifecycleOwner) { schoolScore ->
            binding.schoolScore = schoolScore
        }
    }
}