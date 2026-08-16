package com.example.habithero.presentation.MainActivity

import android.app.Application
import com.example.habithero.di.dataModules.dataModule
import com.example.habithero.di.domainModules.domainModule
import com.example.habithero.presentation.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(dataModule, domainModule, uiModule)
        }
    }
}
