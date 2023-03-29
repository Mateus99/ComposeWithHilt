package com.example.composewithhilt.data.repository

import com.example.composewithhilt.domain.model.Task
import com.example.composewithhilt.data.local.TaskDao
import com.example.composewithhilt.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getAllTasksFlow(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun getTaskFlow(taskId: Long): Flow<Task> {
        return taskDao.getTask(taskId)
    }

    override suspend fun addTask(task: Task) {
        taskDao.upsertTask(task)
    }

    override suspend fun updateTask(taskId: Long) {
        taskDao.updateTask(taskId)
    }

    override suspend fun deleteTask(taskId: Long) {
        taskDao.deleteTask(taskId)
    }
}