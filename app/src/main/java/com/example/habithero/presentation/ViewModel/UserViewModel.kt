package com.example.habithero.presentation.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.DeleteUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.FetchDataUserUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.UpdateUsersHabitUseCase
import com.example.habithero.infrastructure.data.repository.homescreen.UserRepositoryImpl
import com.example.habithero.infrastructure.data.Room.Database.UserDatabase
import com.example.habithero.infrastructure.data.Room.Data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application,
                    private val useCaseForAddHabit: FetchDataUserUseCase,
                    private val useCaseForDeleteHAbit: DeleteUsersHabitUseCase,
                    private val useCaseForUpdateHabit: UpdateUsersHabitUseCase,
                    private var repository: UserRepositoryImpl):
    AndroidViewModel(application)
{
    val readAllData: LiveData<List<User>>
    val users = mutableStateListOf<User>()


    init {
        val userDao = UserDatabase.getDatabaseToHabit(application).userDao()
        repository = UserRepositoryImpl(userDao)
        readAllData = repository.readAllHabit

        readAllData.observeForever { list ->
            users.clear()
            users.addAll(list)
        }
    }

    fun getHabitFromUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            useCaseForAddHabit.execute(user)
        }
    }

    fun updateUserChecked(userId: Int, isChecked: Boolean){
        val index = users.indexOfFirst { it.id == userId }
        if (index != -1) {
            val user = users[index]
            users[index] = user.copy(isChecked = isChecked)
            viewModelScope.launch(Dispatchers.IO) {
                useCaseForUpdateHabit.execute(user.copy(isChecked = isChecked))
            }
        }
    }

    fun deleteUsersHabit(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseForDeleteHAbit.execute(user)
        }
    }


}