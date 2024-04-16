package com.example.intentfilter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScreenC() {
    Column(
        Modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        Text(text = "Screen C")
    }
}