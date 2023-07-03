package com.vcco.a20230704_vcco_nycschools.network.api

import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import com.vcco.a20230704_vcco_nycschools.utils.database.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET

interface NYCSchoolsAPI {
    @GET(NetworkConstants.getSchoolInfo)
    suspend fun getSchoolsInfo(): Response<List<School>>

    @GET(NetworkConstants.getSATScore)
    suspend fun getSATScores(): Response<List<SchoolSATScores>>
}