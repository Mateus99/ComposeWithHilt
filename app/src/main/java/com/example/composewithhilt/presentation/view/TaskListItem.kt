package com.example.composewithhilt.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composewithhilt.domain.model.Task

@Composable
fun TaskListItem(
    task: Task,
    onTaskSelected: (Long) -> Unit,
    onTaskDeleted: (Long) -> Unit,
    updateTaskStatus: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(
                onClick = { onTaskSelected(task.id) },
            )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                if (task.isCompleted) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                tint = Color(0xFF089911),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { updateTaskStatus(task.id) }
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body1
                )
            }
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier.clickable { onTaskDeleted(task.id) }
            )
        }
    }
}

@Preview
@Composable
fun TaskListItemPreview() {
    TaskListItem(
        task = Task(1, "Title", "Description", false),
        onTaskSelected = {},
        onTaskDeleted = {},
        updateTaskStatus = {}
    )
}
