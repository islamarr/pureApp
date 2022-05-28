package com.islam.pureApp.presentation.view

import android.os.Bundle
import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.islam.pureApp.R
import com.islam.pureApp.utils.EspressoIdlingResourceRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun checkWordsListContainer_Displayed() {
        Espresso.onView(withId(R.id.wordListContainer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkRecyclerView_Displayed() {
        Espresso.onView(withId(R.id.wordsList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkToolbarIcons_Displayed() {
        Espresso.onView(withId(R.id.startSearch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.sortList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

   /* @Test
    fun test_search_view() {

        Espresso.onView(withId(R.id.startSearch))
            .perform(ViewActions.click())

        Espresso.onView(withId(androidx.appcompat.R.id.search_src_text)).perform(
            ViewActions.typeText("app"),
            ViewActions.pressKey(KeyEvent.KEYCODE_ENTER)
        )
        Espresso.onView(withId(R.id.wordsList)).check(withItemCount(1))

    }*/

}