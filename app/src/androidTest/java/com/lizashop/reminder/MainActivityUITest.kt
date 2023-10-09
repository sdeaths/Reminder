package com.lizashop.reminder

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @Before
    fun setUp() {
        // Launch the MainActivity using ActivityScenario
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testAddBirthdayItem() {
        // Click on the "Add Birthday" button
        Espresso.onView(ViewMatchers.withId(R.id.addBirthday))
            .perform(ViewActions.click())

        // Enter friend's name and date in the dialog
        Espresso.onView(ViewMatchers.withId(R.id.friendNameEditText))
            .perform(ViewActions.typeText("John Doe"))
        Espresso.onView(ViewMatchers.withId(R.id.dateEditText))
            .perform(ViewActions.typeText("10-02-2023"))

        // Confirm the dialog
        Espresso.onView(ViewMatchers.withText("Add Birthday"))
            .perform(ViewActions.click())

        // Verify that a Snackbar message is displayed
        Espresso.onView(ViewMatchers.withText("Birthday successfully added"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testInvalidDate() {
        // Click on the "Add Birthday" button
        Espresso.onView(ViewMatchers.withId(R.id.addBirthday))
            .perform(ViewActions.click())

        // Enter friend's name and an invalid date in the dialog
        Espresso.onView(ViewMatchers.withId(R.id.friendNameEditText))
            .perform(ViewActions.typeText("John Doe"))
        Espresso.onView(ViewMatchers.withId(R.id.dateEditText))
            .perform(ViewActions.typeText("Date")) // Invalid date

        // Confirm the dialog
        Espresso.onView(ViewMatchers.withText("Add Birthday"))
            .perform(ViewActions.click())

        // Verify that no Snackbar message is displayed for an invalid date
        Espresso.onView(ViewMatchers.withText("Please choose the date"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}