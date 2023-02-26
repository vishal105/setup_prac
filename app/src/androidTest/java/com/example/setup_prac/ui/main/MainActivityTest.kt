package com.example.setup_prac.ui.main

import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.example.setup_prac.R
import com.example.setup_prac.ui.main.main_frag.MainFragment

class MainActivityTest {

    lateinit var scenario: ActivityScenario<MainActivity>

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testing_view() {
        activityScenarioRule.scenario.onActivity { activity ->
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragContainer, MainFragment(), "MainFragment")
                    .commitAllowingStateLoss()
            onView(withId(R.id.fragContainer)).check(matches(isDisplayed()))
        }
    }
}