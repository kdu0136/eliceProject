package com.example.eliceproject

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import com.example.eliceproject.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // start koin
        startKoin {
            // Reference Android context
            androidContext(androidContext = this@GlobalApplication)

            modules(modules = viewModelModules)
        }
    }
}
