package com.example.composewithhilt.di

import android.content.Context
import androidx.room.Room
import com.example.composewithhilt.data.local.TaskDao
import com.example.composewithhilt.data.local.TaskDatabase
import com.example.composewithhilt.domain.repository.TaskRepository
import com.example.composewithhilt.data.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Provides
    fun provideTaskDao(taskDatabase: TaskDatabase): TaskDao {
        return taskDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext appContext: Context) : TaskDatabase {
        return Room.databaseBuilder(
            appContext,
            TaskDatabase::class.java,
            "task-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(taskDao)
    }
}
