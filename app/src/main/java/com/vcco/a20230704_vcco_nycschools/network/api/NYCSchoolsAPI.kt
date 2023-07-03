package com.vcco.a20230704_vcco_nycschools.network.api

import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import com.vcco.a20230704_vcco_nycschools.utils.database.NetworkConstants
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET

interface NYCSchoolsAPI {
    /**
     * Do a network call to retrieve the list of schools listed in the NYC Open Data
     */
    @GET(NetworkConstants.getSchoolInfo)
    fun getSchoolsInfo(): Observable<Response<List<School>>>

    /**
     * Do a network call to retrieve the list of Schools with SAT Scores listed in the NYC Open Data
     */
    @GET(NetworkConstants.getSATScore)
    fun getSATScores(): Observable<Response<List<SchoolSATScores>>>
}