package com.decloudius.jmodupe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.decloudius.jmodupe.presentation.navigation.JMOApp
import com.decloudius.jmodupe.presentation.theme.JMODupeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JMODupeTheme {
                JMOApp()
            }
        }
    }
}
