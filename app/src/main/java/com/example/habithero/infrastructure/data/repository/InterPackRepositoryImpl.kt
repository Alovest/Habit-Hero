package com.example.habithero.infrastructure.data.repository

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.infrastructure.data.Room.Dao.InterPackDao
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

class InterPackRepositoryImpl(override val interPackDao: InterPackDao): InterPackAddRepository {
    override val readAllDataInterPackRepository: LiveData<List<InterPackages>> = interPackDao.getAllItems()
    override suspend fun addInterPackItem(item: InterPackages) {
        super.addInterPackItem(item)
    }
}