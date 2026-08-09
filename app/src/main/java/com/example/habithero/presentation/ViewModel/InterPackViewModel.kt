package com.example.habithero.presentation.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.Room.Data.InterPackages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InterPackViewModel(
    application: Application,
    private val repository: InterPackAddRepository,
    private val useCase: InterPackAddUseCase,
    val folderId: Long
) : AndroidViewModel(application) {

    val readAllData: LiveData<List<InterPackages>> = repository.getItemsFromFolder(folderId)

    private val allInterPackList = MutableLiveData<List<InterPackages>>()
    val interPackList: LiveData<List<InterPackages>> = allInterPackList

    init {
        readAllData.observeForever { data ->
            allInterPackList.postValue(data ?: emptyList())
        }
    }

    fun addInterPackViewModel(item: InterPackages) {
        viewModelScope.launch(Dispatchers.IO) {
            if (item.folderId == folderId && item.folderId > 0L) {
                useCase.execute(item)
            } else {
                android.util.Log.e("TODO_ERROR", "Cannot insert task: Invalid parent folder ID alignment.")
            }
        }
    }
}
