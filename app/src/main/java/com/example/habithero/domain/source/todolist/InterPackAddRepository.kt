package com.example.habithero.domain.source.todolist

import androidx.lifecycle.LiveData
import com.example.habithero.infrastructure.data.Room.Dao.InterPackDao
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

interface InterPackAddRepository {
    val interPackDao: InterPackDao
    val readAllDataInterPackRepository: LiveData<List<InterPackages>>
    suspend fun addInterPackItem(item: InterPackages) {
        interPackDao.getInterPacksItem(item)
    }
}