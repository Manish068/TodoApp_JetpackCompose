package com.devopworld.todoapp.repository

import com.devopworld.todoapp.data.TodoDao
import com.devopworld.todoapp.data.model.TodoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao){

    val getAllTasks: Flow<List<TodoTask>> = todoDao.getAllTasks()
    val sortByLowPriority : Flow<List<TodoTask>> = todoDao.sortByLowPriority()
    val sortByHighPriority : Flow<List<TodoTask>> = todoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<TodoTask> {
        return todoDao.getSelectedTask(taskId)
    }

    fun searchTask(searchQuery:String) : Flow<List<TodoTask>>{
        return todoDao.searchDatabase(searchQuery)
    }

    suspend fun addTask(todoTask: TodoTask){
        return todoDao.insertTodo(todoTask)
    }

    suspend fun updateTask(todoTask: TodoTask){
        return todoDao.updateTask(todoTask)
    }

    suspend fun removeTask(todoTask: TodoTask){
        return todoDao.deleteTask(todoTask)
    }

    suspend fun deleteAllTasks() = todoDao.deleteAllTasks()


}