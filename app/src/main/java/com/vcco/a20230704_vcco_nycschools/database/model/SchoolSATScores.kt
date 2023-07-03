package com.vcco.a20230704_vcco_nycschools.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TSchoolSAtScores")
data class SchoolSATScores(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "dbn")
    val dbn: String,
    @ColumnInfo(name = "school_name")
    val schoolName: String,
    @ColumnInfo(name = "sat_critical_reading_avg_score")
    val criticalReadingAvgScore: Int,
    @ColumnInfo(name = "sat_math_avg_score")
    val mathAvgScore: Int,
    @ColumnInfo(name = "sat_writing_avg_score")
    val writingAvgScore: Int
)