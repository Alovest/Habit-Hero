package com.example.habithero.infrastructure.data.repository.todoList

import androidx.lifecycle.LiveData
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.infrastructure.data.Room.Dao.InterPackDao
import com.example.habithero.infrastructure.data.Room.Data.InterPackages

class InterPackRepositoryImpl(override val interPackDao: InterPackDao): InterPackAddRepository {
    override val readAllDataInterPackRepository: LiveData<List<InterPackages>> = interPackDao.getAllItems()
    override suspend fun addInterPackItem(item: InterPackages) {
            interPackDao.getInterPacksItem(item)
    }

    override fun getItemsFromFolder(folderId: Long): LiveData<List<InterPackages>> {
        return interPackDao.getItemsFromFolder(folderId)
    }
}