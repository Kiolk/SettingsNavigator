package com.github.kiolk.settingsnavigator.presentation

import android.app.Application
import com.github.kiolk.settingsnavigator.di.modules.viewModuleModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    viewModuleModel
                )
            )
        }
    }
}