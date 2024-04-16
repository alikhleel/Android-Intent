package com.example.intentfilter.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
fun ScreenB(navController: NavHostController, navBackStackEntry: NavBackStackEntry) {
    navController.previousBackStackEntry?.savedStateHandle?.set(
        "message",
        "This is a message from Screen B"
    )
    val userId = navBackStackEntry.arguments?.getString("userId")

    Column(
        Modifier
            .background(Color.Blue)
            .fillMaxSize()
            .clickable { navController.navigate("ScreenC") }
    ) {
        Text(text = "Screen B: ".plus(userId))
    }
}