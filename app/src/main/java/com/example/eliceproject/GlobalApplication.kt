package com.example.eliceproject

import android.app.Application
import com.example.eliceproject.di.remoteModules
import com.example.eliceproject.di.repositoryModules
import com.example.eliceproject.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // start koin
        startKoin {
            // Reference Android context
            androidContext(androidContext = this@GlobalApplication)

            modules(modules = remoteModules)
            modules(modules = repositoryModules)
            modules(modules = viewModelModules)
        }
    }
}
