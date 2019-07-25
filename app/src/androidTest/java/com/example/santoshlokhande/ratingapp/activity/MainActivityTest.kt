package com.example.santoshlokhande.ratingapp.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.santoshlokhande.ratingapp.CustomAssertions.Companion.hasItemCount
import com.example.santoshlokhande.ratingapp.CustomMatchers.Companion.withItemCount
import com.example.santoshlokhande.ratingapp.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)


    @Before
    fun setup(){

    }

    @Test
    fun countBooks() {
        onView(withId(R.id.recycler_view))
                .check(matches(withItemCount(10)))
    }

    @Test
    fun countBookWithViewAssertion() {
        onView(withId(R.id.recycler_view))
                .check(hasItemCount(10))
    }

    @After
    fun close(){

    }

}