package com.example.habithero.infrastructure.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habithero.infrastructure.data.Room.Data.TodoList

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun getTodoList(todo: TodoList): Long

    @Query("SELECT * FROM todo_database ORDER BY Todoid ASC")
    fun getAllTodo(): LiveData<List<TodoList>>
}