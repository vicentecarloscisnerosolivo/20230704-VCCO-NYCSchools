package com.vcco.a20230704_vcco_nycschools.ui.schools

import android.content.Context
import android.net.wifi.WifiManager
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.vcco.a20230704_vcco_nycschools.MainActivity
import com.vcco.a20230704_vcco_nycschools.util.MainCoroutineRule
import com.vcco.a20230704_vcco_nycschools.util.RecycleViewUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import kotlin.random.Random

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class SchoolTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val recyclerViewUtils = RecycleViewUtils()
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun initTestClass() {
        runBlocking {
            activityScenario = recyclerViewUtils.createScenario()
        }
    }

    @After
    fun closeScenario() {
        activityScenario.close()
    }

    @Test
    fun testRecyclerViewVisible() {
        onView(recyclerViewUtils.getRecyclerView()).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewClick() {
        recyclerViewUtils.recyclerViewSchoolClick(Random.nextInt(5))
        pressBack()
        onView(recyclerViewUtils.getRecyclerView()).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewDoubleClick() {
        val random = Random.nextInt(4)
        recyclerViewUtils.recyclerViewSchoolClick(random)
        pressBack()
        recyclerViewUtils.recyclerViewSchoolClick(random + 1)
        pressBack()
        onView(recyclerViewUtils.getRecyclerView()).check(matches(isDisplayed()))
    }
}