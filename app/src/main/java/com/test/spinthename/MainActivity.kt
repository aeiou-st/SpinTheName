package com.test.spinthename

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.spinthename.ui.theme.SpinTheNameTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinTheNameTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "names") {
        composable("names") {
            NamesScreen { namesList ->
                navController.navigate("spinner/${namesList.joinToString(",")}")
            }
        }
        composable(
            route = "spinner/{names}",
            arguments = listOf(navArgument("names") { type = NavType.StringType })
        ) { backStackEntry ->
            val namesList = backStackEntry.arguments?.getString("names")?.split(",") ?: emptyList()
            SpinnerScreen(namesList) { randomName ->
                navController.navigate("random/$randomName")
            }
        }
        composable(
            route = "random/{randomName}",
            arguments = listOf(navArgument("randomName") { type = NavType.StringType })
        ) { backStackEntry ->
            val randomName = backStackEntry.arguments?.getString("randomName") ?: ""
            RandomScreen(randomName) {
                navController.popBackStack()
            }
        }
    }
}
