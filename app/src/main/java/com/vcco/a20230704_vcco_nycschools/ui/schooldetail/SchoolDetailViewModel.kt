package com.vcco.a20230704_vcco_nycschools.ui.schooldetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import com.vcco.a20230704_vcco_nycschools.repository.NYCSchoolsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(private val repository: NYCSchoolsRepository) :
    ViewModel() {
    //School Detail
    private val _schoolDetail = MutableLiveData<School>()

    /**
     * Live data of School and Detail in the NYC Open Data
     */
    val schoolDetail: LiveData<School> = _schoolDetail

    private val _schoolScore = MutableLiveData<SchoolSATScores>()

    /**
     * Live fata of School Scores in the NYC Open Data
     */
    val schoolScore: LiveData<SchoolSATScores> = _schoolScore

    /**
     * Get School Detail from the NYC Open Data and store in SchoolDetail
     */

    fun getSchoolDetail(id: String) {
        viewModelScope.launch {
            _schoolDetail.postValue(repository.getSchoolSDetail(id))
        }
    }

    /**
     * Get School Score from the NYC Open Data and store in schoolScore
     */

    fun getSchoolScore(id: String) {
        viewModelScope.launch {
            _schoolScore.postValue(repository.getSchoolSatScore(id))
        }
    }

}