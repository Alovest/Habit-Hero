package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter_table")
data class HabitCounter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val value: Int = 0
)
