package com.vcco.a20230704_vcco_nycschools.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vcco.a20230704_vcco_nycschools.database.model.School

@Dao
interface SchoolDao : DAO<School> {

    @Query("SELECT * FROM TSchool ORDER BY dbn")
    suspend fun getSchoolsByID(): List<School>

    @Query("SELECT * FROM TSchool ORDER BY school_name")
    suspend fun getSchoolsByName(): List<School>

    @Query("SELECT * FROM TSchool WHERE city LIKE :city")
    suspend fun getSchoolsInCity(city: String): List<School>

    @Query("SELECT * FROM TSchool WHERE dbn = :id")
    suspend fun getSchool(id: String): School

    @Transaction
    suspend fun updateSchools(schools: List<School>) {
        deleteSchools()
        insert(schools)
    }

    @Query("DELETE FROM TSchool")
    suspend fun deleteSchools()
}