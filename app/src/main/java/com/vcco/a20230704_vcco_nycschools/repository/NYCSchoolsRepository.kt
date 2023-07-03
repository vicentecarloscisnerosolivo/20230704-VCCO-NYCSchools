package com.vcco.a20230704_vcco_nycschools.repository

import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores

interface NYCSchoolsRepository {
    /**
     * Use this method to get the data of all schools listed in the NYC Open Data service
     * @return List of schools ordered by ID
     */
    suspend fun getSchools(): List<School>?

    /**
     * Use this method to get the score of the select school
     * @return Detail of the school
     */
    suspend fun getSchoolSatScore(id: String): SchoolSATScores
}