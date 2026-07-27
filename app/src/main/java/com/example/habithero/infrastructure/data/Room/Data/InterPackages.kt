package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "inter_packages",
    foreignKeys = [
        ForeignKey(
            entity = TodoList::class,
            parentColumns = ["Todoid"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class InterPackages(
    @PrimaryKey(autoGenerate = true)
    val InterPackagesid: Long = 0,
    val titleOfInterPackages: String,
    val folderId: Long
)
