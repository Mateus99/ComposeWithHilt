package com.example.composewithhilt.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object NewTaskScreen : Screen("new_task_screen")

    fun withBooleanArgs(vararg args: Boolean): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
