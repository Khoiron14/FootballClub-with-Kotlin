package com.khoiron.footballmatchschedule

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.khoiron.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.khoiron.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.khoiron.footballmatchschedule.R.id.*
import com.khoiron.footballmatchschedule.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Khoiron14 on 28/11/18.
 */
@RunWith(AndroidJUnit4::class)
class AppBehaviourTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        SystemClock.sleep(3000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))

        testMatchFragment(10)

        onView(withId(next_match)).perform(click())
        testMatchFragment(10)

        onView(withId(favorites)).perform(click())
        testMatchFragment(1)
    }

    private fun testMatchFragment(position: Int) {
        SystemClock.sleep(2000)
        onView(withId(list_event)).check(matches(isDisplayed()))
        onView(withId(list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(withId(list_event)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )

        SystemClock.sleep(2000)
        onView(withId(home_team)).check(matches(isDisplayed()))

        testFavorite()
    }

    private fun testFavorite() {
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        if (isDisplayed().matches(ic_add_to_favorites)) {
            onView(withText("Added to favorite")).check(matches(isDisplayed()))
        }

        if (isDisplayed().matches(ic_added_to_favorites)) {
            onView(withText("Removed to favorite")).check(matches(isDisplayed()))
        }

        SystemClock.sleep(2000)
        pressBack()
    }
}