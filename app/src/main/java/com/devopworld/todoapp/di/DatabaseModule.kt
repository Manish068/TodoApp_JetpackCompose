package com.devopworld.todoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.devopworld.todoapp.data.TodoDatabase
import com.devopworld.todoapp.util.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabases(@ApplicationContext context: Context)
    = Room.databaseBuilder(context,
        TodoDatabase::class.java,
        DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDao(database: TodoDatabase) = database.todoDao()


}