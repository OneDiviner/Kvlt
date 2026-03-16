package com.example.impl.di

import com.example.impl.data.TracksRepositoryImpl
import com.example.impl.domain.TracksRepository
import org.koin.dsl.module

val tracksRepositoryModule = module {
    single<TracksRepository> {
        TracksRepositoryImpl()
    }
}