package com.example.habithero.di.Modules

import com.example.habithero.domain.source.homescreen.DeleteUsersHabitRepository
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.DeleteUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.FetchDataUserUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.UpdateUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.repository.InterPackRepository
import com.example.habithero.infrastructure.data.repository.TodoListRepository
import com.example.habithero.infrastructure.data.repository.homescreen.UserRepositoryImpl
import com.example.habithero.infrastructure.data.Room.Database.InterPackDatabase
import com.example.habithero.infrastructure.data.Room.Database.TodoDatabase
import com.example.habithero.infrastructure.data.Room.Database.UserDatabase
import com.example.habithero.infrastructure.data.repository.homescreen.DeleteUsersHabitImpl
import com.example.habithero.infrastructure.data.repository.homescreen.UpdateUsersHabitImpl
import com.example.habithero.presentation.ViewModel.InterPackViewModel
import com.example.habithero.presentation.ViewModel.TodoListViewModel
import com.example.habithero.presentation.ViewModel.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val appModule = module {
    //Users part
    single { UserDatabase.getDatabaseToHabit(androidApplication()).userDao() }
       // repositories
    single { UserRepositoryImpl(get()) }
    single { UpdateUsersHabitImpl(get()) }
    single { DeleteUsersHabitImpl(get()) }
       // use cases
    single { DeleteUsersHabitUseCase(get()) }
    single { FetchDataUserUseCase(get()) }
    single { UpdateUsersHabitUseCase(get()) }

    viewModel {
        UserViewModel(androidApplication(),
            get(),
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