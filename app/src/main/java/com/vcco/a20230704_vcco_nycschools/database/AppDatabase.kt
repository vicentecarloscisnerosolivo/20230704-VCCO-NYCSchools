package com.vcco.a20230704_vcco_nycschools.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolDao
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolSATScoresDao
import com.vcco.a20230704_vcco_nycschools.database.model.School
import com.vcco.a20230704_vcco_nycschools.database.model.SchoolSATScores

@Database(
    entities = [School::class, SchoolSATScores::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun schoolDao(): SchoolDao
    abstract fun schoolSchoolSATScoresDao(): SchoolSATScoresDao
}