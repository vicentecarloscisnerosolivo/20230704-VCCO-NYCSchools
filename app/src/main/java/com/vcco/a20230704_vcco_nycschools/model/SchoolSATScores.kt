package com.vcco.a20230704_vcco_nycschools.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vcco.a20230704_vcco_nycschools.utils.database.DatabaseConstants
import com.vcco.a20230704_vcco_nycschools.utils.database.NetworkConstants

/**
 * Create School Sat Model, using same object for database and Network Call
 */
@Entity(tableName = DatabaseConstants.schoolSAtScoresTable)
data class SchoolSATScores(
    @SerializedName(NetworkConstants.dbnResponse)
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = DatabaseConstants.dbnColumn)
    val dbn: String,
    @SerializedName(NetworkConstants.schoolNameResponse)
    @ColumnInfo(name = DatabaseConstants.schoolNameColumn)
    val schoolName: String,
    @SerializedName(NetworkConstants.numOfSatTestTakersResponse)
    @ColumnInfo(name = DatabaseConstants.numOfSatTestTakersColumn)
    val numOfSatTestTakers: String,
    @SerializedName(NetworkConstants.satCriticalReadingAvgScoreResponse)
    @ColumnInfo(name = DatabaseConstants.satCriticalReadingAvgScoreColumn)
    val criticalReadingAvgScore: String,
    @SerializedName(NetworkConstants.satMathAvgScoreResponse)
    @ColumnInfo(name = DatabaseConstants.satMathAvgScoreColumn)
    val mathAvgScore: String,
    @SerializedName(NetworkConstants.satWritingAvgScoreResponse)
    @ColumnInfo(name = DatabaseConstants.satWritingAvgScoreColumn)
    val writingAvgScore: String
)