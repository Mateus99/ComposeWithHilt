package com.example.composewithhilt.data.local

import androidx.room.*
import com.example.composewithhilt.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :taskId")
    fun getTask(taskId: Long): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTask(task: Task)

    @Query("UPDATE task SET isCompleted = NOT isCompleted WHERE id = :taskId")
    suspend fun updateTask(taskId: Long)

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteTask(taskId: Long)
}