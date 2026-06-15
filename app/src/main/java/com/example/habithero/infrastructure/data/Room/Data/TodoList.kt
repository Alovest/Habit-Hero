package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "todo_database",
    foreignKeys = [
        ForeignKey(
            entity = InterPackages::class,
            parentColumns = ["InterPackagesid"],
            childColumns = ["IPid"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class TodoList (
    @PrimaryKey(autoGenerate = true)
    val Todoid: Long = 0,
    val titleOfTodo: String,
    val IPid: Long = 0
)