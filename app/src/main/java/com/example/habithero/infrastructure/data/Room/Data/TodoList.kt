package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_database")
data class TodoList (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titleOfTodo: String,
)