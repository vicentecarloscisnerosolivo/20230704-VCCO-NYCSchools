package com.vcco.a20230704_vcco_nycschools.repository

import android.net.ConnectivityManager
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
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class RepositoryTest {
    @Mock
    lateinit var schoolDao: SchoolDao

    @Mock
    lateinit var schoolSATScoresDao: SchoolSATScoresDao

    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Mock
    lateinit var api: NYCSchoolsAPI

    @InjectMocks
    lateinit var networkManager: NetworkManager

    lateinit var repository: NYCSchoolsRepository
    lateinit var schoolList: List<School>
    lateinit var schoolDetail: School
    lateinit var schoolScore: SchoolSATScores

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
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
        networkManager = NetworkManager(connectivityManager)
        repository = NYCSchoolsRepositoryImp(api, schoolDao, schoolSATScoresDao, networkManager)
    }

    @Test
    fun checkSchoolDetail() {
        runBlocking {
            Mockito.`when`(repository.getSchoolSDetail(schoolDetail.dbn)).thenReturn(
                schoolDetail
            )
            val response = repository.getSchoolSDetail(schoolDetail.dbn)
            assert(response.dbn == schoolDetail.dbn)
        }
    }

    @Test
    fun checkSchoolSATScores() {
        runBlocking {
            Mockito.`when`(repository.getSchoolSatScore(schoolDetail.dbn)).thenReturn(
                schoolScore
            )
            val response = repository.getSchoolSatScore(schoolDetail.dbn)
            assert(response.dbn == schoolDetail.dbn)
        }
    }
}