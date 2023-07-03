package com.vcco.a20230704_vcco_nycschools.repository

import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores

interface NYCSchoolsRepository {
    suspend fun getSchools(): List<School>
    suspend fun getSchoolSatScore(id: String): SchoolSATScores
}