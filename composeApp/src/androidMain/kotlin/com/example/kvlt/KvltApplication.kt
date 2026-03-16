package com.example.kvlt

import android.app.Application
import com.example.impl.di.contentResolverModule
import com.example.kvlt.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class KvltApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(Level.INFO)
            androidContext(this@KvltApplication)
        }
    }
}