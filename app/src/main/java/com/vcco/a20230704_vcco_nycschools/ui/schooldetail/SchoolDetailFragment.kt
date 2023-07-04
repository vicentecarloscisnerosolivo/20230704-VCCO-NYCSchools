package com.vcco.a20230704_vcco_nycschools.ui.schooldetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vcco.a20230704_vcco_nycschools.R
import com.vcco.a20230704_vcco_nycschools.databinding.FragmentSchoolDetailBinding
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import com.vcco.a20230704_vcco_nycschools.utils.database.DatabaseConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private val viewModel: SchoolDetailViewModel by viewModels()
    private lateinit var binding: FragmentSchoolDetailBinding

    //map value for pin
    private var googleMap: GoogleMap? = null

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
        //Add observer
        addObservers()
        //addClicklisteners
        addClickListeners()
        //addMapListener
        addMapListener()
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
            schoolDetail?.let {
                addMapMarker(it)
            }
        }
        //observer SchoolSATScores
        viewModel.schoolScore.observe(viewLifecycleOwner) { schoolScore ->
            if (schoolScore == null) {
                binding.schoolScore = SchoolSATScores(
                    DatabaseConstants.NI,
                    DatabaseConstants.NI,
                    DatabaseConstants.NA,
                    DatabaseConstants.NI,
                    DatabaseConstants.NI,
                    DatabaseConstants.NI
                )
            } else {
                binding.schoolScore = schoolScore
            }
        }
    }

    /**
     * Add CLick listeners to redirect to call, send email or go to webpage
     */

    private fun addClickListeners() {

    }

    /**
     * Check when map is ready
     */
    private fun addMapListener() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            googleMap = map
        }
    }

    /**
     * Add Map Marker and move camera to marker
     * @param schoolDetails
     */
    private fun addMapMarker(schoolDetails: School) {
        if (schoolDetails.latitude != null && schoolDetails.longitude != null) {
            val position = LatLng(schoolDetails.latitude, schoolDetails.longitude)
            googleMap?.addMarker(
                MarkerOptions()
                    .title(schoolDetails.schoolName)
                    .position(position)
            )
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
            binding.pogressBarSchoolDetail.visibility = View.GONE
        }
    }
}