package com.devopworld.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devopworld.todoapp.data.model.TodoTask
import java.util.*

@Database(entities = [TodoTask::class],version = 1,exportSchema = false)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao():TodoDao
}