package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo_database",
)
data class TodoList (
    @PrimaryKey(autoGenerate = true)
    val Todoid: Long = 0,
    val titleOfTodo: String,
)