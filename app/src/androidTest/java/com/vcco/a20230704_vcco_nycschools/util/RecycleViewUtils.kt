package com.vcco.a20230704_vcco_nycschools.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import com.vcco.a20230704_vcco_nycschools.R
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.vcco.a20230704_vcco_nycschools.MainActivity
import org.hamcrest.Matcher

class RecycleViewUtils {

    fun recyclerViewSchoolClick(position: Int) {
        performClick(position, getRecyclerView())
    }


    fun getRecyclerView() = ViewMatchers.withId(R.id.listSchools)
    fun createScenario() =
        ActivityScenario.launch((MainActivity::class.java))

    private fun performClick(position: Int, recyclerViewMatcher: Matcher<View>) {
        onView(recyclerViewMatcher)
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    position,
                    ViewActions.click()
                )
            )
    }
}