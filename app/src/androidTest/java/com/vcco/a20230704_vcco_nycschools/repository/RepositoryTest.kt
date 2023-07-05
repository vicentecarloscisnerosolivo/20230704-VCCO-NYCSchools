package com.vcco.a20230704_vcco_nycschools.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolDao
import com.vcco.a20230704_vcco_nycschools.database.dao.SchoolSATScoresDao
import com.vcco.a20230704_vcco_nycschools.model.School
import com.vcco.a20230704_vcco_nycschools.model.SchoolSATScores
import com.vcco.a20230704_vcco_nycschools.network.api.NYCSchoolsAPI
import com.vcco.a20230704_vcco_nycschools.network.manager.NetworkManager
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class RepositoryTest {
    @Mock
    lateinit var schoolDao: SchoolDao

    @Mock
    lateinit var schoolSATScoresDao: SchoolSATScoresDao

    @Mock
    lateinit var api: NYCSchoolsAPI

    @Mock
    lateinit var networkManager: NetworkManager

    lateinit var repository: NYCSchoolsRepository
    lateinit var schoolList: List<School>
    lateinit var schoolDetail: School
    lateinit var schoolScore: SchoolSATScores

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schoolDetail = School(
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
        schoolScore = SchoolSATScores("1d", "TestSchool", "10", "450", "450", "450")
        schoolList = listOf(
            schoolDetail,
            School(
                "2d",
                "TestSchool2",
                "There is a happySchool",
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
        )
        repository = NYCSchoolsRepositoryImp(api, schoolDao, schoolSATScoresDao, networkManager)
    }

    @Test
    fun checkListOfSchools() {
        runBlocking {
            Mockito.`when`(repository.getSchools()).thenAnswer {
                repository.schoolList.postValue(schoolList)
            }
            var getSchools = repository.schoolList
            repository.getSchools()
            assert(getSchools.value != null)
            assert(getSchools.value?.size == 2)
        }
    }
}