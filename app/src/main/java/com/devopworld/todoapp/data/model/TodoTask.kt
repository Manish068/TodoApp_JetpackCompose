package com.devopworld.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devopworld.todoapp.util.Constant.DATABASE_TABLE_NAME

@Entity(tableName = DATABASE_TABLE_NAME)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    val Title:String ,
    val description:String,
    val priority:Priority
)