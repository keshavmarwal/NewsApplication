package com.example.newapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import com.example.newapplication.Screens.App

import com.example.newapplication.Screens.HomeUI
import com.example.newapplication.ui.theme.NewApplicationTheme
import kotlinx.coroutines.DelicateCoroutinesApi


class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    val viewModel = MyViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  App(modifier = Modifier.padding(innerPadding),viewModel = viewModel)

                }
            }
        }
    }


}



