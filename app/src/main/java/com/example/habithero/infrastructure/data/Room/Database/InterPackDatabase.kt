package com.example.habithero.infrastructure.data.Room.Database

import android.content.Context
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habithero.infrastructure.data.Room.Dao.InterPackDao
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

@Database(entities = [InterPackages::class], version = 1, exportSchema = false)
abstract class InterPackDatabase: RoomDatabase() {
    abstract fun interPackDao(): InterPackDao

    companion object {
        @Volatile
        private var INSTANCE: InterPackDatabase? = null

        fun getDatabaseToInterPack(context: Context): InterPackDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InterPackDatabase::class.java,
                    "inter_packages"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}