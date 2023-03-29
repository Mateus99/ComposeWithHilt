package com.example.composewithhilt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composewithhilt.presentation.view.NewTaskScreen
import com.example.composewithhilt.presentation.view.TaskListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            TaskListScreen(navController = navController)
        }
        composable(
            route = Screen.NewTaskScreen.route + "?taskId={taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { entry ->
            NewTaskScreen(
                navController = navController,
                taskId = entry.arguments?.getString("taskId")?.toLong()
            )
        }
    }
}