package com.example.habithero.infrastructure.data.Room.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habithero.infrastructure.data.Room.Data.HabitCounter
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitCounterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun increment(counter: HabitCounter)

    @Query("SELECT * FROM counter_table WHERE id = 1")
    fun getCounterValue(): Flow<HabitCounter?>
}