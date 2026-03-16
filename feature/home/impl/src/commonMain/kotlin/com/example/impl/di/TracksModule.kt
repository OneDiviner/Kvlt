package com.example.impl.di

import org.koin.dsl.module

val tracksModule = module {
    includes(
        tracksRepositoryModule
    )
}