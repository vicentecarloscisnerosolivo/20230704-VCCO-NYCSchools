package com.vcco.a20230704_vcco_nycschools.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class DataBaseTest {
    private lateinit var schoolDatabase: AppDatabase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDB() {
        schoolDatabase = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        schoolDatabase.close()
    }

    @Test
    fun insertSchoolSATScoreTest() {
        runBlocking {
            val schoolScore = SchoolSATScores("1d", "TestSchool", "10", "450", "450", "450")
            schoolDatabase.schoolSchoolSATScoresDao().insert(listOf(schoolScore))
            val result = schoolDatabase.schoolSchoolSATScoresDao().getSchoolScores(schoolScore.dbn)
            assert(result.dbn == schoolScore.dbn)
        }
    }

    @Test
    fun getSchoolSATScoreTest() {
        runBlocking {
            val schoolScore = SchoolSATScores("1d", "TestSchool", "10", "450", "450", "450")
            schoolDatabase.schoolSchoolSATScoresDao().insert(listOf(schoolScore))
            val result = schoolDatabase.schoolSchoolSATScoresDao().getSchoolsWithScore()
            assert(result.size == 1)
        }
    }

    @Test
    fun insertSchoolTest() {
        runBlocking {
            val school = School(
                "1d",
                "TestSchool",
                "There is a school pretty",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
            schoolDatabase.schoolDao().insert(listOf(school))
            val result = schoolDatabase.schoolDao().getSchool(school.dbn)
            assert(result.dbn == school.dbn)
        }
    }

    @Test
    fun getSchoolTest() {
        runBlocking {
            val school = School(
                "1d",
                "TestSchool",
                "There is a school pretty",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            )
            schoolDatabase.schoolDao().insert(listOf(school))
            val result = schoolDatabase.schoolDao().getSchoolsByID()
            assert(result != null)
        }
    }

}