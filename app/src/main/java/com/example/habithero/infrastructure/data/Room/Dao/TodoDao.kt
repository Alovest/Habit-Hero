package com.example.habithero.infrastructure.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habithero.infrastructure.data.Room.Data.TodoList

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getTodoList(todo: TodoList)

    @Query("SELECT * FROM todo_database ORDER BY id ASC")
    fun getAllTodo(): LiveData<List<TodoList>>
}