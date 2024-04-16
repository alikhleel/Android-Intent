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
fun ScreenA(navBackStackEntry: NavBackStackEntry, navController: NavHostController) {
    Column(
        Modifier
            .background(Color.Green)
            .fillMaxSize()
            .clickable { navController.navigate("ScreenB/testUser") }
    ) {
        val message = navBackStackEntry.savedStateHandle.get<String>("message")
        Text(
            text = "This is Screen A".plus(
                message.orEmpty()
            )
        )
    }
}