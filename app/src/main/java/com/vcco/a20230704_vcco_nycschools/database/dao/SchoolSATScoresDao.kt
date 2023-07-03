package com.vcco.a20230704_vcco_nycschools.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores

@Dao
interface SchoolSATScoresDao: DAO<SchoolSATScores> {
    @Query("SELECT * FROM TSchoolSAtScores WHERE dbn = :id")
    suspend fun getSchoolScores(id: String): SchoolSATScores

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY dbn")
    suspend fun getSchoolsWithScore(): List<SchoolSATScores>

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_critical_reading_avg_score desc")
    suspend fun getSchoolFromAvgScoreDesc(): List<SchoolSATScores>

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_critical_reading_avg_score asc")
    suspend fun getSchoolsFromAvgScoreAsc(): List<SchoolSATScores>

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_math_avg_score desc")
    suspend fun getSchoolFromMathAvgScoreDesc(): List<SchoolSATScores>

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_math_avg_score asc")
    suspend fun getSchoolFromMathAvgScoreAsc(): List<SchoolSATScores>

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_writing_avg_score desc")
    suspend fun getSchoolFromWritingAvgScoreDesc(): List<SchoolSATScores>

    @Query("SELECT * FROM TSchoolSAtScores ORDER BY sat_writing_avg_score asc")
    suspend fun getSchoolFromWritingAvgScoreAsc(): List<SchoolSATScores>

    @Transaction
    suspend fun updateSchoolScores(schoolScores: List<SchoolSATScores>){
        deleteSchoolScores()
        insert(schoolScores)
        deleteSchoolIfNotHaveScore()
    }

    @Query("DELETE FROM TSchoolSAtScores")
    suspend fun deleteSchoolScores()

    @Query("DELETE FROM TSchoolSAtScores WHERE dbn NOT IN(SELECT s.dbn FROM TSchool s)")
    suspend fun deleteSchoolIfNotHaveScore()
}