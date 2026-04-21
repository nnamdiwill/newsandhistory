package com.example.newsandhistory.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NewsInfoNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = FirstScreen.List.path ) {
        composable(FirstScreen.List.path) {
            NewsListScreen(
                viewModel = hiltViewModel(),
                onNewsRowTap = { newsIndex ->
                    navController.navigate("${FirstScreen.Details.path}/$newsIndex")
                },
                onSettingsTap = { navController.navigate(FirstScreen.Settings.path) },
                onAboutTap = { navController.navigate(FirstScreen.About.path) },
            )
        }


        composable(
            route = "${FirstScreen.Details.path}/{newsIndex}",
            arguments = listOf(navArgument("newsIndex") { type = NavType.IntType }),
        ) { backStackEntry ->
            val newsIndex = backStackEntry.arguments!!.getInt("newsIndex")
            NewsDetailScreen(
                newsIndex = newsIndex,
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )

        }
        composable(FirstScreen.About.path) {
            AboutScreen(
                onNavigateUp = { navController.navigateUp() },
            )
        }

        composable(FirstScreen.Settings.path) {
            SettingsScreen(
                viewModel = hiltViewModel(),
                onNavigateUp = { navController.navigateUp() },
            )
        }
    }

}
