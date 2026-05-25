package com.example.habithero.di.Modules

import com.example.habithero.domain.source.homescreen.DeleteUsersHabitRepository
import com.example.habithero.domain.source.homescreen.UpdateUsersHabit
import com.example.habithero.domain.source.homescreen.UsersRepository
import com.example.habithero.domain.source.todolist.InterPackAddRepository
import com.example.habithero.domain.source.todolist.TodoListRepository
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.CreateTodoUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.DeleteUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.FetchDataUserUseCase
import com.example.habithero.domain.usecase.UseCasesForHomeScreen.UpdateUsersHabitUseCase
import com.example.habithero.domain.usecase.UseCasesForTodoListScreen.InterPack.InterPackAddUseCase
import com.example.habithero.infrastructure.data.repository.InterPackRepositoryImpl
import com.example.habithero.infrastructure.data.repository.TodoListRepositoryImpl
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
import org.koin.dsl.factory
import org.koin.dsl.module

val appModule = module {
    //Users part
       //database
    single {
        UserDatabase.getDatabaseToHabit(androidApplication()).userDao()
    }
       // repositories
    single<UsersRepository> {
        UserRepositoryImpl(get())
    }
    single<UpdateUsersHabit> {
        UpdateUsersHabitImpl(get())
    }
    single<DeleteUsersHabitRepository> {
        DeleteUsersHabitImpl(get())
    }
       // use cases
    factory<DeleteUsersHabitUseCase>{
        DeleteUsersHabitUseCase(get())
    }
    factory<FetchDataUserUseCase> {
        FetchDataUserUseCase(get())
    }
    factory<UpdateUsersHabitUseCase> {
        UpdateUsersHabitUseCase(get())
    }
       //viewModel
    viewModel {
        UserViewModel(androidApplication(),
            get<FetchDataUserUseCase>(),
            get<DeleteUsersHabitUseCase>(),
            get<UpdateUsersHabitUseCase>(),
            get<UsersRepository>()
        ) }
    //

    //TodoLists part
        //use case
    factory<CreateTodoUseCase> {
        CreateTodoUseCase(get())
    }
        //repository
    single<TodoListRepository> {
        TodoListRepositoryImpl(get())
    }
        //database
    single {
        TodoDatabase.getDatabaseToTodo(androidApplication()).todoDao()
    }
        //viewModel
    viewModel{
        TodoListViewModel(androidApplication(),
        get<CreateTodoUseCase>(),
        get<TodoListRepository>()
        )
    }
    //

    //InterPackages
         //use case
    factory<InterPackAddUseCase> {
        InterPackAddUseCase(get())
    }
         //repository
    single<InterPackAddRepository> {
        InterPackRepositoryImpl(get())
    }
         //database
    single {
        InterPackDatabase.getDatabaseToInterPack(androidApplication()).interPackDao()
    }
         //viewModel
    viewModel{
        InterPackViewModel(androidApplication(),
            get<InterPackRepositoryImpl>(),
            get<InterPackAddUseCase>()
        )
    }
    //
}