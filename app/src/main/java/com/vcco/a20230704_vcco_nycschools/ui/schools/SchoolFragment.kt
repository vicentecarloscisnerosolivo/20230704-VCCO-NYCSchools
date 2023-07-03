package com.vcco.a20230704_vcco_nycschools.ui.schools

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.vcco.a20230704_vcco_nycschools.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment where is listed the Schools of NYC OpenData
 */
@AndroidEntryPoint
class SchoolFragment : Fragment() {

    private val viewModel: SchoolViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schools, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Request the list of the schools from the liveData
        requestSchools()
        //addObservers
        addObservers()
    }

    /**
     * Make the request to live data to retrieve the list of the schools
     */
    private fun requestSchools(){
        viewModel.getSchoolsList()
    }

    /**
     * Observer the data from SchoolsViewModel
     */
    private fun addObservers() {
        viewModel.schoolsList.observe(viewLifecycleOwner) { schoolsList ->
            schoolsList?.let {
                if (!it.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Salioooooooooo", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}