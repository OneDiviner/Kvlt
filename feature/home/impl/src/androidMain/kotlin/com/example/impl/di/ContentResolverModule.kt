package com.example.impl.di

import android.content.ContentResolver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val contentResolverModule = module {
    single<ContentResolver> { androidContext().contentResolver }
}