package com.example.bleremote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.bleremote.ui.theme.BLERemoteTheme
import com.juul.kable.Advertisement


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    var mPrefs = getPreferences(MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BLERemoteTheme {
                navController = rememberNavController()
                CompositionLocalProvider(Advertisement) {
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

