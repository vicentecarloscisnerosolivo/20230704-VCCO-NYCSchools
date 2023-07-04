package com.vcco.a20230704_vcco_nycschools.ui.schools

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vcco.a20230704_vcco_nycschools.R
import com.vcco.a20230704_vcco_nycschools.databinding.FragmentSchoolsBinding
import com.vcco.a20230704_vcco_nycschools.ui.schools.adapter.SchoolAdapter
import com.vcco.a20230704_vcco_nycschools.ui.schools.adapter.SchoolAdapterListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment where is listed the Schools of NYC OpenData
 */
@AndroidEntryPoint
class SchoolFragment : Fragment() {

    private val viewModel: SchoolViewModel by viewModels()
    private lateinit var binding: FragmentSchoolsBinding

    //recyclerview elements
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var schoolsAdapter: SchoolAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Init recyclerview
        initRecyclerView()
        setAdapter()
        //Request the list of the schools from the liveData
        requestSchools()
        //addObservers
        addObservers()
        //addClickListener
        addClickListener()
    }

    /**
     * Create instance of FragmentSchoolBinding
     */
    private fun createBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_schools,
            container,
            false
        )
    }

    /**
     * Init recyclerview with LayoutManager
     */
    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(requireContext())
        binding.listSchools.layoutManager = layoutManager
    }

    /**
     * Set Adapter to Schools RecyclerView
     */
    private fun setAdapter() {
        schoolsAdapter = SchoolAdapter(SchoolAdapterListener {
            viewModel.onSchoolClicked(it)
        })
        binding.listSchools.adapter = schoolsAdapter
    }

    /**
     * Make the request in viewModel to retrieve the list of the schools
     */
    private fun requestSchools() {
        viewModel.getSchoolsList()
    }

    /**
     * Observer the data from SchoolsViewModel
     */
    private fun addObservers() {
        //observe when get list of the schools
        viewModel.schoolsList.observe(viewLifecycleOwner) { schoolsList ->
            schoolsList?.let {
                schoolsAdapter.submitList(it)
                binding.pogressBarSchools.visibility = View.GONE
            }
        }
        //Observer if there is any error in the repository
        viewModel.errorMessage.observe(viewLifecycleOwner) {error->
            error?.let {
                binding.pogressBarSchools.visibility = View.GONE
                binding.listSchools.visibility = View.GONE
                binding.errorMessageSchools.visibility = View.VISIBLE
                binding.errorMessageSchools.text = error
                binding.errorMessageSchoolsButton.visibility = View.VISIBLE
            }
        }
        //observe when user click a school to see details
        viewModel.navigationToSchoolDetail.observe(viewLifecycleOwner) { schoolId ->
            schoolId?.let {
                //Use safe args to init next fragment
                findNavController().navigate(
                    SchoolFragmentDirections.actionSchoolsragmentToSchoolDetailFragment(
                        schoolId
                    )
                )
                //Reset value
                viewModel.onSchoolDetailNavigated()
            }
        }
    }

    /**
     * Click listener for retry fetch data, only will be visible when is a error
     */
    private fun addClickListener(){
        binding.errorMessageSchoolsButton.setOnClickListener{
            requestSchools()
            binding.pogressBarSchools.visibility = View.VISIBLE
            binding.listSchools.visibility = View.VISIBLE
            binding.errorMessageSchools.visibility = View.GONE
            binding.errorMessageSchoolsButton.visibility = View.GONE
        }
    }
}