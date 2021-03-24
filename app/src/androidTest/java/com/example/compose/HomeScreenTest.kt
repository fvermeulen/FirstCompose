package com.example.compose

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.compose.ui.main.AppMainContent
import com.example.compose.ui.theme.AppTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val toggleState = mutableStateOf(0)
            getSharedPreferences().edit().putInt("PREFERENCE_TITLE_STATE", 0).commit()
            AppTheme(toggleState) {
                Surface(color = MaterialTheme.colors.background) {
                    AppMainContent(toggleState, getSharedPreferences())
                }
            }
        }
    }

    @Test
    fun defaultFirstIsEnabled() {
        composeTestRule.onNodeWithTag("Name Text").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Name Text").assertTextEquals("First")

        assertEquals(getState(getSharedPreferences()), 0)
    }

    @Test
    fun toggleButtonWillChangeState() {
        composeTestRule.onNodeWithText("Toggle").assertExists()
        composeTestRule.onNodeWithText("Toggle").assertIsDisplayed()
        composeTestRule.onNodeWithText("Toggle").performClick()
        composeTestRule.onNodeWithTag("Name Text").assertTextEquals("Second")

        assertEquals(getState(getSharedPreferences()), 1)
    }

    private fun getState(sharedPreferences: SharedPreferences) =
        sharedPreferences.getInt("PREFERENCE_TITLE_STATE", 0)

    private fun getSharedPreferences(): SharedPreferences {
        return getInstrumentation().targetContext.getSharedPreferences("", Context.MODE_PRIVATE)
    }
}
