package com.example.habithero.presentation.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habithero.domain.UseCasesForHomeScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.Repository.InterPackRepository
import com.example.habithero.infrastructure.data.Room.Data.InterPackages
import com.example.habithero.infrastructure.data.Room.Database.InterPackDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InterPackViewModel(application: Application,
    private var repository: InterPackRepository,
    private val useCase: InterPackAddUseCase
): AndroidViewModel(application) {

    val readAllData: LiveData<List<InterPackages>>

    private val allInterPackList = MutableLiveData<List<InterPackages>>()

    val interPackList: LiveData<List<InterPackages>> = allInterPackList

    init {
        val todoDao = InterPackDatabase.getDatabaseToInterPack(application).interPackDao()
        repository = InterPackRepository(todoDao)
        readAllData = repository.readAllDataInterPackRepository
    }

    fun addInterPackViewModel(item: InterPackages){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.execute(item)
        }
    }

}