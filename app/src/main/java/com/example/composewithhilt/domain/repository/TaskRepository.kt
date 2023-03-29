package com.example.composewithhilt.domain.repository

import com.example.composewithhilt.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasksFlow(): Flow<List<Task>>
    fun getTaskFlow(taskId: Long): Flow<Task>
    suspend fun addTask(task: Task)
    suspend fun updateTask(taskId: Long)
    suspend fun deleteTask(id: Long)
}
