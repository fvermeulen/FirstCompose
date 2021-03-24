package com.example.compose.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.R
import com.example.compose.ui.theme.primaryBlue
import com.example.compose.ui.theme.primaryRed

@Preview
@Composable
fun AppMainContent(
    state: MutableState<Int> = mutableStateOf(0),
    sharedPreferences: SharedPreferences? = null
) {
    val name = stringResource(
        id =
        when (state.value) {
            0 -> {
                R.string.title_first
            }
            1 -> {
                R.string.title_second
            }
            else -> {
                R.string.title_third
            }
        }
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name, color = Color.White) },
                backgroundColor = setColor(state)
            )
        },
        content = {
            ScreenContent(
                name = name,
                state = state,
                sharedPreferences = sharedPreferences
            )
        })
}

@Composable
fun ScreenContent(
    name: String,
    state: MutableState<Int>,
    sharedPreferences: SharedPreferences? = null
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = name,
            color = setColor(state = state),
            modifier = Modifier.semantics { testTag = "Name Text" },
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            )
        )
        Spacer(modifier = Modifier.size(60.dp))
        Button(onClick = {
            state.value = if (state.value >= 2) {
                0
            } else {
                state.value + 1
            }
            handleState(context, sharedPreferences, state.value)
        }, Modifier.semantics { testTag = "Home Screen Toggle" }) {
            Text(text = stringResource(R.string.home_toggle))
            Layout.Alignment.ALIGN_CENTER
        }
    }
}

@Composable
private fun setColor(state: MutableState<Int>) =
    when (state.value) {
        0 -> {
            primaryRed
        }
        1 -> {
            primaryBlue
        }
        else -> {
            Color.Green
        }
    }

private fun handleState(
    context: Context,
    sharedPreferences: SharedPreferences? = null,
    state: Int
) {
    val key = context.getString(R.string.preference_title_state)
    sharedPreferences?.edit()?.putInt(key, state)?.apply()
}
