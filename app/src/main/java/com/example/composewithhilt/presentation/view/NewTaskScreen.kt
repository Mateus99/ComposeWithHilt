package com.example.composewithhilt.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composewithhilt.domain.model.Task
import com.example.composewithhilt.presentation.viewmodel.TaskListViewModel

@Composable
fun NewTaskScreen(
    viewModel: TaskListViewModel = hiltViewModel(),
    navController: NavController,
    taskId: Long?
) {
    val currentTask = viewModel.currentTask.collectAsState()
    LaunchedEffect(true) {
        viewModel.checkExistingTask(taskId)
    }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = currentTask.value.title,
            onValueChange = { viewModel.setTitle(it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = currentTask.value.description,
            onValueChange = { viewModel.setDescription(it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newTask = Task(
                    id = taskId ?: 0,
                    title = currentTask.value.title,
                    description = currentTask.value.description,
                    isCompleted = currentTask.value.isCompleted
                )
                viewModel.upsertTask(newTask)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }
    }
}

@Preview
@Composable
fun NewTaskScreenPreview() {
    NewTaskScreen(navController = rememberNavController(), taskId = 2)
}