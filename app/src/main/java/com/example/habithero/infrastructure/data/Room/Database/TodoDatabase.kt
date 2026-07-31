package com.example.habithero.infrastructure.data.Room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habithero.infrastructure.data.Room.Dao.HabitCounterDao
import com.example.habithero.infrastructure.data.Room.Dao.InterPackDao
import com.example.habithero.infrastructure.data.Room.Dao.TodoDao
import com.example.habithero.infrastructure.data.Room.Dao.UserDao
import com.example.habithero.infrastructure.data.Room.Data.HabitCounter
import com.example.habithero.infrastructure.data.Room.Data.InterPackages
import com.example.habithero.infrastructure.data.Room.Data.TodoList
import com.example.habithero.infrastructure.data.Room.Data.User

@Database(entities = [TodoList::class, InterPackages::class, User::class, HabitCounter::class], version = 5, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun interPackDao(): InterPackDao
    abstract fun userDao(): UserDao

    abstract fun habitCounterDao(): HabitCounterDao
    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun getDatabaseToTodo(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}