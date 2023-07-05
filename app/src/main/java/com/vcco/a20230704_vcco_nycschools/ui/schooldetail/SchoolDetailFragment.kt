package com.vcco.a20230704_vcco_nycschools.ui.schooldetail

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.LocationServices
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
import com.vcco.a20230704_vcco_nycschools.utils.permission.PermissionsConstants
import com.vcco.a20230704_vcco_nycschools.utils.permission.PermissionsUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * Show the school detail and SAT Score, view the position in the map and contact info
 */
@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private val viewModel: SchoolDetailViewModel by viewModels()
    private lateinit var binding: FragmentSchoolDetailBinding

    //Permision request code
    val permissionLocationCode = 12

    //map value for pin
    private var googleMap: GoogleMap? = null

    //School Detail will be used for call external view like phone call email or webPage
    private lateinit var schoolDetail: School

    //create val to retrieve id from previous screen
    private val args: SchoolDetailFragmentArgs by navArgs()

    //factor of convertion metters to miles
    val milesToMeters = 1609.34

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
                this.schoolDetail = it
                verifyLocationPermissions()
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
        binding.locationDescription.setOnClickListener {
            //open maps to the specific direction
            val geoUri = "http://maps.google.com/maps?q=loc:${schoolDetail.latitude},${schoolDetail.longitude}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
            startActivity(intent)
        }
        binding.phoneNumber.setOnClickListener {
            //open dial to user choose call or not
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${schoolDetail.phoneNumber}"))
            startActivity(intent)
        }

        binding.faxNumber.setOnClickListener {
            //open dial to user choose call or not
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${schoolDetail.faxNumber}"))
            startActivity(intent)
        }
        binding.emailAddress.setOnClickListener {
            //Open predefined app to user create the email to request info
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${schoolDetail.schoolEmail}")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // makes return to the app instead the email app
            startActivity(intent)
        }
        binding.webPage.setOnClickListener {
            //check if contians http to be able to parse correctly the URl
            val url = if (!schoolDetail.webSite?.contains("http://")!!)
                "http://${schoolDetail.webSite}"
            else
                schoolDetail.webSite
            //Launch Chrome browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.android.chrome")
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                // Chrome browser presumably not installed so allow user to choose instead
                intent.setPackage(null)
                startActivity(intent)
            }
        }
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

    /**
     * Check if permissions are granted to perfom action
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionLocationCode) {// If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED))
            ) {
                calculateDistance()
            } else {
                Toast.makeText(
                    requireContext(),
                    PermissionsConstants.callPermissionDenied,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    /**
     * Check location permission and if permissions are giving calculate distance between user and school
     */
    fun verifyLocationPermissions() {
        if (PermissionsUtils.isPermissionGranted(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) && PermissionsUtils.isPermissionGranted(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            calculateDistance()
        } else {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                permissionLocationCode
            )
        }
    }

    /**
     * Calculate distance between school and user
     */
    private fun calculateDistance() {
        //declare fusedLocation to be used latter
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        //Check if location permissions are given, avoding compilation error
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //check if gps is enabled, to get the most precise location
            if (PermissionsUtils.isGPSEnabled(requireContext())) {
                //add listener for lastLocation
                fusedLocationClient.lastLocation.addOnCompleteListener() {
                    //only continue if is success action
                    if (it.isSuccessful) {
                        val userLocation = it.result
                        //check null safe to userLocation
                        userLocation?.let {
                            //required for Location:distance Between here will me stored the result of the distance
                            val results = FloatArray(1)
                            //null safe for latitude or longitude could exist the problem of null in one of the two values
                            schoolDetail.latitude?.let { latitude ->
                                schoolDetail.longitude?.let { longitude ->
                                    //calculate distance between user
                                    Location.distanceBetween(
                                        it.latitude, it.longitude,
                                        latitude, longitude, results
                                    )
                                    //put visible elements for calculate distance
                                    binding.distanceText.visibility = View.VISIBLE
                                    binding.distance.visibility = View.VISIBLE
                                    //distance given in meters, convert to milles
                                    binding.distance.text = getString(R.string.miles_away,results[0]/milesToMeters)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}