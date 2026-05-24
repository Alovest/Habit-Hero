package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_database")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isChecked: Boolean = false,
    val title: String,
    val isDone: Boolean = false,
    val date: String = ""
)