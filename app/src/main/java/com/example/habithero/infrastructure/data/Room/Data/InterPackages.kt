package com.example.habithero.infrastructure.data.Room.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inter_packages")
data class InterPackages(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titleOfInterPackages: String
)