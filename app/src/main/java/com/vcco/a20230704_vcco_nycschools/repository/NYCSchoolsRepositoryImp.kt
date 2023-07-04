package com.vcco.a20230704_vcco_nycschools.repository

import androidx.lifecycle.MutableLiveData
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolDao
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolSATScoresDao
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.network.api.NYCSchoolsAPI
import com.vcco.a20230704_vcco_nycschools.network.manager.NetworkManager
import com.vcco.a20230704_vcco_nycschools.utils.database.NetworkConstants
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class NYCSchoolsRepositoryImp @Inject constructor(
    private val api: NYCSchoolsAPI,
    private val schoolDao: SchoolDao,
    private val scoresDao: SchoolSATScoresDao,
    private val networkManager: NetworkManager
) :
    NYCSchoolsRepository {

    override val schoolList = MutableLiveData<List<School>?>()
    override val errorMessage = MutableLiveData<String>()

    override suspend fun getSchools() {
        //Get the data from the database
        val result = schoolDao.getSchoolsByID()
        //if the list comes empty or null call web service to retrieve data and populate the data base
        if (result.isNullOrEmpty()) {
            //The web service will fill the list only if online
            if (networkManager.isOnLine()) {
                getSchoolsFromWeb()
            } else {
                errorMessage.postValue(NetworkConstants.errorNoInternet)
            }
        } else {
            schoolList.postValue(result)
        }
    }

    override suspend fun getSchoolSDetail(id: String) = schoolDao.getSchool(id)

    override suspend fun getSchoolSatScore(id: String) = scoresDao.getSchoolScores(id)

    /**
     * Use to sync the School data from the Web Service of NYC Open Data
     */
    private fun getSchoolsFromWeb() {
        //Create the network for the Data of the Schools observed in the main tread
        api.getSchoolsInfo().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Verify if the call is successful
                if (it.isSuccessful) {
                    GlobalScope.launch {
                        //if the network call is successful call the service to get the Schools Score
                        getSchoolsScore(it.body())
                    }
                }
            }, {
                //If there is an error print the error
                it.printStackTrace()
                errorMessage.postValue(NetworkConstants.errorRepository)
            })
        //returning the Data of the web service
    }

    /**
     * Use to sync the School Scores data from the Web Service of NYC Open Data
     */

    private fun getSchoolsScore(schools: List<School>?) {
        //Create the network for the Data of the Schools Scores observed in the main tread
        api.getSATScores().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Verify if the call is successful
                if (it.isSuccessful) {
                    val result = it.body()
                    //Populate Schools Table in the database
                    schools?.let { schoolsList ->
                        GlobalScope.launch {
                            schoolList.postValue(schoolDao.updateSchools(schoolsList))
                        }
                    }
                    //Populate Schools Scores in the database
                    result?.let { scoresList ->
                        GlobalScope.launch {
                            scoresDao.updateSchoolScores(scoresList)
                        }
                    }
                }
            }, {
                //If there is an error print the error
                it.printStackTrace()
                errorMessage.postValue(NetworkConstants.errorRepository)
            })
    }
}
