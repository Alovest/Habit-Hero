package com.example.habithero.infrastructure.data.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

@Dao
interface InterPackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getInterPacksItem(item: InterPackages)

    @Query("SELECT * FROM inter_packages ORDER BY id ASC")
    fun getAllItems(): LiveData<List<InterPackages>>
}