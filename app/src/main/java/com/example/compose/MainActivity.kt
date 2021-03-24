package com.example.compose

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import com.example.compose.ui.main.AppMainContent
import com.example.compose.ui.theme.AppTheme

class MainActivity : AppCompatActivity() {
    private val toggleState = mutableStateOf(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toggleState.value = getState()
        setContent {
            AppTheme(toggleState) {
                AppMainContent(toggleState, getSharedPreferences())
            }
        }
    }

    private fun getState(): Int {
        val sharedPreferences = getSharedPreferences()
        val key = getString(R.string.preference_title_state)
        return sharedPreferences.getInt(key, 0)
    }

    private fun getSharedPreferences() = this@MainActivity.getPreferences(Context.MODE_PRIVATE)
}
