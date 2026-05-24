package com.example.habithero.di.Modules

import com.example.habithero.domain.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.domain.UseCasesForHomeScreen.DeleteUsersHabit
import com.example.habithero.domain.UseCasesForHomeScreen.FetchDataUserUseCase
import com.example.habithero.domain.UseCasesForHomeScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.Repository.InterPackRepository
import com.example.habithero.infrastructure.data.Repository.TodoListRepository
import com.example.habithero.infrastructure.data.Repository.UserRepository
import com.example.habithero.infrastructure.data.Room.Database.InterPackDatabase
import com.example.habithero.infrastructure.data.Room.Database.TodoDatabase
import com.example.habithero.infrastructure.data.Room.Database.UserDatabase
import com.example.habithero.presentation.ViewModel.InterPackViewModel
import com.example.habithero.presentation.ViewModel.TodoListViewModel
import com.example.habithero.presentation.ViewModel.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //Users part
    single { UserDatabase.getDatabaseToHabit(androidApplication()).userDao() }
    single { UserRepository(get()) }
    single { DeleteUsersHabit(get()) }
    single { FetchDataUserUseCase(get()) }

    viewModel {
        UserViewModel(androidApplication(),
            get(),
            get(),
            get()
        ) }
    //

    //TodoLists part
    single { CreateTodoUseCase(get()) }
    single { TodoListRepository(get()) }
    single { TodoDatabase.getDatabaseToTodo(androidApplication()).todoDao() }

    viewModel{
        TodoListViewModel(androidApplication(),
        get(),
        get()
        )
    }
    //

    //InterPackages
    single { InterPackAddUseCase(get()) }
    single { InterPackRepository(get()) }
    single { InterPackDatabase.getDatabaseToInterPack(androidApplication()).interPackDao() }

    viewModel{
        InterPackViewModel(androidApplication(),
            get(),
            get()
        )
    }
    //


}