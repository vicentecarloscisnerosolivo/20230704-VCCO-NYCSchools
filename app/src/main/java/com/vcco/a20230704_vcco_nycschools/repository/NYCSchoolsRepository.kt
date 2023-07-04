package com.vcco.a20230704_vcco_nycschools.repository

import androidx.lifecycle.MutableLiveData
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores

/**
 * School repository, used to retrieve data for schools with calls to database and in case of data base is empty do sync of data and show the results
 */
interface NYCSchoolsRepository {

    val schoolList: MutableLiveData<List<School>?>
    /**
     * Mutable live data, listen data of School List, if the local database is empty, listen data from network response
     */
    /**
     * Mutable live data, to notify user that was an error retrieving Data
     */
    val errorMessage: MutableLiveData<String>
    /**
     * Use this method to get the data of all schools listed in the NYC Open Data service
     * @return List of schools ordered by ID
     */
    suspend fun getSchools()

    /**
     * Use this method to get the detail of the select school
     * @return Detail of the school
     */
    suspend fun getSchoolSDetail(id: String): School

    /**
     * Use this method to get the score of the select school
     * @return Data
     */
    suspend fun getSchoolSatScore(id: String): SchoolSATScores
}