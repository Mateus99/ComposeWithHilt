package com.example.composewithhilt.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composewithhilt.navigation.Navigation
import com.example.composewithhilt.ui.theme.ComposeWithHiltTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWithHiltTheme {
                Navigation()
            }
        }
    }
}
