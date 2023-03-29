package com.example.composewithhilt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composewithhilt.domain.model.Task
import com.example.composewithhilt.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow(emptyList<Task>())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _currentTask = MutableStateFlow(Task())
    val currentTask = _currentTask.asStateFlow()

    fun setTitle(title: String) {
        _currentTask.value = _currentTask.value.copy(title = title)
    }

    fun setDescription(description: String) {
        _currentTask.value = _currentTask.value.copy(description = description)
    }

    init {
        viewModelScope.launch {
            taskRepository.getAllTasksFlow().collect {
                _tasks.value = it
            }
        }
    }

    fun upsertTask(task: Task) {
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

    fun checkExistingTask(taskId: Long?) {
        taskId?.let {
            viewModelScope.launch {
                taskRepository.getTaskFlow(it).collect { task ->
                    _currentTask.value = task
                }
            }
        }
    }

    fun deleteTask(taskId: Long) {
        viewModelScope.launch {
            taskRepository.deleteTask(taskId)
        }
    }

    fun updateTaskStatus(taskId: Long) {
        viewModelScope.launch {
            taskRepository.updateTask(taskId)
        }
    }
}
