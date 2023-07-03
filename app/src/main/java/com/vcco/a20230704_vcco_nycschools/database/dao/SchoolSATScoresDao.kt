package com.vcco.a20230704_vcco_nycschools.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores

/**
 * Dao SchoolSATScores: with methods to get info, insert new info or delete info
 */
@Dao
interface SchoolSATScoresDao : DAO<SchoolSATScores> {
    /**
     * Get the school score
     * @param id<String>
     * @return SchoolSATScores of specific school
     */
    @Query("SELECT * FROM TSchoolSAtScores WHERE dbn = :id")
    suspend fun getSchoolScores(id: String): SchoolSATScores

    /**
     * Get List of schools Scores ordered for School id
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY dbn")
    suspend fun getSchoolsWithScore(): List<SchoolSATScores>

    /**
     * Get List of schools Scores ordered for School Reading score from best to worst
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_critical_reading_avg_score desc")
    suspend fun getSchoolFromAvgScoreDesc(): List<SchoolSATScores>

    /**
     * Get List of schools Scores ordered for School Reading score from worst to best
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_critical_reading_avg_score asc")
    suspend fun getSchoolsFromAvgScoreAsc(): List<SchoolSATScores>

    /**
     * Get List of schools Scores ordered for School Math score from best to worst
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_math_avg_score desc")
    suspend fun getSchoolFromMathAvgScoreDesc(): List<SchoolSATScores>

    /**
     * Get List of schools Scores ordered for School Math score from worst to best
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_math_avg_score asc")
    suspend fun getSchoolFromMathAvgScoreAsc(): List<SchoolSATScores>

    /**
     * Get List of schools Scores ordered for School Writing score from best to worst
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_writing_avg_score desc")
    suspend fun getSchoolFromWritingAvgScoreDesc(): List<SchoolSATScores>

    /**
     * Get List of schools Scores ordered for School Math score from worst to best
     * @return the list of schools
     */
    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_writing_avg_score asc")
    suspend fun getSchoolFromWritingAvgScoreAsc(): List<SchoolSATScores>

    /**
     * Sync the data retrieve from the web service, delete the current SchoolsScores and update with new info
     * @param schoolsScores
     */
    @Transaction
    suspend fun updateSchoolScores(schoolScores: List<SchoolSATScores>) {
        deleteSchoolScores()
        insert(schoolScores)
        deleteSchoolIfNotHaveScore()
    }

    /**
     * Delete all data from SchoolsSATScores Table
     */
    @Query("DELETE FROM TSchoolSAtScores")
    suspend fun deleteSchoolScores()

    /**
     * Delete data from SchoolsSATScores Table where the data is not School table
     */
    @Query("DELETE FROM TSchoolSAtScores WHERE dbn NOT IN(SELECT s.dbn FROM TSchool s)")
    suspend fun deleteSchoolIfNotHaveScore()
}