package com.example.habithero.infrastructure.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habithero.infrastructure.data.Room.Data.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getHabitUser(user: User)

    @Update
    suspend fun updateUser(user: User)
    @Query("SELECT * FROM habit_database ORDER BY id ASC")
    fun getAllHabits(): LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User): Int
}