package com.vcco.a20230704_vcco_nycschools.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vcco.a20230704_vcco_nycschools.model.School

/**
 *  Dao Schools: with methods to get info, insert new info or delete info
 */
@Dao
interface SchoolDao : DAO<School> {

    /**
     * Get List of schools ordered for School id
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchool ORDER BY dbn")
    suspend fun getSchoolsByID(): List<School>?

    /**
     * Get List of schools ordered for School Name
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchool ORDER BY school_name")
    suspend fun getSchoolsByName(): List<School>

    /**
     * Get List of schools ordered by cities
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchool WHERE city LIKE :city")
    suspend fun getSchoolsInCity(city: String): List<School>

    /**
     * Get the Data for a specific School searched by id
     * @param id
     */
    @Query("SELECT * FROM TSchool WHERE dbn = :id")
    suspend fun getSchool(id: String): School

    /**
     * Sync the data retrieve from the web service, delete the current Schools and update with new info
     * @param schools
     */
    @Transaction
    suspend fun updateSchools(schools: List<School>) {
        deleteSchools()
        insert(schools)
    }

    /**
     * Delete all data from Schools Table
     */
    @Query("DELETE FROM TSchool")
    suspend fun deleteSchools()
}