package com.example.intentfilter

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.intentfilter.screens.ScreenA
import com.example.intentfilter.screens.ScreenB
import com.example.intentfilter.screens.ScreenC


enum class Screen {
    ScreenA,
    ScreenB,
    ScreenC
}

sealed class NavigationItem(val route: String) {
    data object ScreenA : NavigationItem(Screen.ScreenA.name)
    data object ScreenB : NavigationItem(Screen.ScreenB.name)
    data object ScreenC : NavigationItem(Screen.ScreenC.name)
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.ScreenA.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.ScreenA.route) {
            ScreenA(it, navController)
        }
        composable(
            NavigationItem.ScreenB.route.plus("/{userId}"),
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {
            ScreenB(navController, it)
        }
        composable(NavigationItem.ScreenC.route) {
            ScreenC()
        }

        composable(
            route = "detail",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern="https://example.com/{userId}"
                    action = Intent.ACTION_VIEW
                }
            ),

            arguments = listOf(navArgument("userId"){
                type = NavType.IntType
                defaultValue = -1
            })
        ) { entry ->
            val id = entry.arguments?.getInt("userId")
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Detail Screen with id $id",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}