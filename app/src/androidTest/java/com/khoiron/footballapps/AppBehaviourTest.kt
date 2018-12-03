package com.khoiron.footballapps

import android.os.SystemClock
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.khoiron.footballapps.R.drawable.ic_add_to_favorites
import com.khoiron.footballapps.R.drawable.ic_added_to_favorites
import com.khoiron.footballapps.R.id.*
import com.khoiron.footballapps.ui.main.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Khoiron14 on 03/12/18.
 */
@RunWith(AndroidJUnit4::class)
class AppBehaviourTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour() {
        SystemClock.sleep(2000)
        onView(withId(navigation)).check(matches(isDisplayed()))

        testFragment(10, list_event)
        testSearch("united")

        onView(withId(view_pager)).perform(swipeLeft())
        testFragment(10, list_event)
        testSearch("united")

        onView(withId(navigation_team)).perform(click())
        testFragment(10, list_team)
        testSearch("united")

        onView(withId(navigation_favorite)).perform(click())
        onView(withId(view_pager)).perform(swipeLeft())
    }

    private fun testFragment(position: Int, recylerView: Int) {
        SystemClock.sleep(2000)
        onView(
            allOf(
                withId(recylerView),
                isDisplayed()
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(allOf(withId(recylerView), isDisplayed())).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )

        SystemClock.sleep(1000)
        if (recylerView != list_player) testFavorite()

        if (recylerView == list_team) {
            SystemClock.sleep(1000)
            onView(withText("PLAYERS")).perform(click())
            testFragment(5, list_player)
        }

        pressBack()
    }

    private fun testFavorite() {
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        if (isDisplayed().matches(ic_add_to_favorites)) {
            onView(ViewMatchers.withText("Added to favorite")).check(matches(isDisplayed()))
        }

        if (isDisplayed().matches(ic_added_to_favorites)) {
            onView(ViewMatchers.withText("Removed to favorite")).check(matches(isDisplayed()))
        }
    }

    private fun testSearch(text: String) {
        onView(allOf(withId(menu_search), isClickable())).perform(click())
        onView(withId(search_src_text)).perform(click())
            .perform(typeTextIntoFocusedView(text))
        SystemClock.sleep(1000)
        pressBack()
        pressBack()
    }
}