package com.example.santoshlokhande.ratingapp.activity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.example.santoshlokhande.ratingapp.CustomAssertions.Companion.hasItemCount
import com.example.santoshlokhande.ratingapp.CustomMatchers.Companion.withItemCount
import com.example.santoshlokhande.ratingapp.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest{


    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)


    @Test
    fun countBooks() {
        onView(withId(R.id.recycler_view))
                .check(matches(withItemCount(10)))
    }

    /**
     *   Failed this test for testing purpose
     */

    @Test
    fun countBookWithViewAssertion() {
        onView(withId(R.id.recycler_view))
                .check(hasItemCount(100))
    }

}