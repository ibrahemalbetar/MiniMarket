package com.ibrahimalbitar.minimarket

import android.app.Application
import com.ibrahimalbitar.minimarket.injection.DatabaseModule
import com.ibrahimalbitar.minimarket.injection.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        setupKoin(this)
    }

    private fun setupKoin(app: Application) {
        startKoin {
            androidContext(app)
            modules(
                listOf(
                    DatabaseModule(),
                    ViewModelsModule()
                )
            )
        }
    }

}