package com.vcco.a20230704_vcco_nycschools.ui.schools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.repository.NYCSchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View Model attached to SchoolFragment
 */
@HiltViewModel
class SchoolViewModel @Inject constructor(private val repository: NYCSchoolsRepository) :
    ViewModel() {
    //schools
    private val _schoolsList = MutableLiveData<List<School>?>()

    /**
     * LiveData for List of the Schools Listed in the NYC Open Data
     */
    val schoolsList: LiveData<List<School>?> = _schoolsList

    //navigation

    private val _navigationToSchoolDetail = MutableLiveData<String?>()

    /**
     * LiveData for clickListener, Listen when user click a school and navigate to next screen
     */
    val navigationToSchoolDetail = _navigationToSchoolDetail

    /**
     * Get List of the schools listed in the NYC Open Data and store in schoolList LiveData
     */
    fun getSchoolsList() {
        //launch the coroutine for the suspend fun
        viewModelScope.launch {
            //Use postValue to avoid problem in the UI Thread
            _schoolsList.postValue(repository.getSchools())
        }
    }

    /**
     * Set Id from click school to Navigate to School detail
     */
    fun onSchoolClicked(id: String) {
        _navigationToSchoolDetail.postValue(id)
    }

    /**
     * Reset id from clickedSchool
     */
    fun onSchoolDetailNavigated() {
        _navigationToSchoolDetail.value = null
    }

}