package com.vcco.a20230704_vcco_nycschools.ui.schools

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.vcco.a20230704_vcco_nycschools.MainActivity
import com.vcco.a20230704_vcco_nycschools.R
import com.vcco.a20230704_vcco_nycschools.util.MainCoroutineRule
import com.vcco.a20230704_vcco_nycschools.util.RecycleViewUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class SchoolDetailTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val recyclerViewUtils = RecycleViewUtils()
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun initTestClass() {
        runBlocking {
            activityScenario = recyclerViewUtils.createScenario()
        }
        recyclerViewUtils.recyclerViewSchoolClick(0)
    }

    @After
    fun closeScenario() {
        activityScenario.close()
    }

    @Test
    fun testOverviewVisible() {
        onView(getOverviewData()).check(matches(isDisplayed()))
    }

    @Test
    fun testIfOverviewIsNotEmpty() {
        onView(getOverviewData())
            .check(matches(not(withText(""))))
    }


    private fun getOverviewData() = ViewMatchers.withId(R.id.schoolOverview)


}