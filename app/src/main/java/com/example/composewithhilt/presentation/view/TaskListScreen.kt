package com.example.composewithhilt.presentation.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composewithhilt.navigation.Screen
import com.example.composewithhilt.presentation.viewmodel.TaskListViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = hiltViewModel(),
    navController: NavController
) {
    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Tasks") }
            )
        },
        content = {
            LazyColumn {
                items(tasks) { task ->
                    TaskListItem(
                        task,
                        onTaskSelected = {
                            navController.navigate(Screen.NewTaskScreen.route + "?taskId=$it")
                        },
                            onTaskDeleted = { viewModel.deleteTask(it) },
                        updateTaskStatus = { viewModel.updateTaskStatus(it) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.NewTaskScreen.route)
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add task")
            }
        }
    )
}

@Preview
@Composable
fun TaskListScreenPreview() {
    TaskListScreen(navController = rememberNavController())
}