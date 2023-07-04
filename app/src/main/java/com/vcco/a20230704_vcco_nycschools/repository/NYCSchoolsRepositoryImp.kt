package com.vcco.a20230704_vcco_nycschools.repository

import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolDao
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolSATScoresDao
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import com.vcco.a20230704_vcco_nycschools.network.api.NYCSchoolsAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NYCSchoolsRepositoryImp @Inject constructor(
    private val api: NYCSchoolsAPI,
    private val schoolDao: SchoolDao,
    private val scoresDao: SchoolSATScoresDao
) :
    NYCSchoolsRepository {


    override suspend fun getSchools(): List<School>? {
        //Get the data from the database
        var result = schoolDao.getSchoolsByID()
        //if the list comes empty or null call web service to retrieve data and populate the data base
        if (result.isNullOrEmpty()) {
            //The web service will fill the list
            result = getSchoolsFromWeb()
        }
        //return the data to viewModel
        return result
    }

    override suspend fun getSchoolSDetail(id: String) = schoolDao.getSchool(id)

    override suspend fun getSchoolSatScore(id: String) = scoresDao.getSchoolScores(id)

    /**
     * Use to sync the School data from the Web Service of NYC Open Data
     */
    private suspend fun getSchoolsFromWeb(): List<School>? {
        var result: List<School>? = null
        //Create the network for the Data of the Schools observed in the main tread
        api.getSchoolsInfo().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Verify if the call is successful
                if (it.isSuccessful) {
                    result = it.body()
                    GlobalScope.launch {
                        //if the network call is successful call the service to get the Schools Score
                        getSchoolsScore(result)
                    }
                }
            }, {
                //If there is an error print the error
                //TODO: VCCO: Throw and error connection
                it.printStackTrace()
            })
        //returning the Data of the web service
        return result
    }

    /**
     * Use to sync the School Scores data from the Web Service of NYC Open Data
     */

    private suspend fun getSchoolsScore(schools: List<School>?) {
        //Create the network for the Data of the Schools Scores observed in the main tread
        api.getSATScores().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Verify if the call is successful
                if (it.isSuccessful) {
                    var result = it.body()
                    //Populate Schools Table in the database
                    schools?.let { schoolsList ->
                        GlobalScope.launch {
                            schoolDao.updateSchools(schoolsList)
                        }
                    }
                    //Populate Schools Scores in the databse
                    result?.let { scoresList ->
                        GlobalScope.launch {
                            scoresDao.updateSchoolScores(scoresList)
                        }
                    }
                }
            }, {
                //If there is an error print the error
                //TODO: VCCO: Throw and error connection
                it.printStackTrace()
            })
    }
}
