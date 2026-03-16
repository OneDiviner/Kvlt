package com.example.kvlt.di

import com.example.impl.di.tracksModule
import org.koin.dsl.module

val commonModule = module {
    includes(
        tracksModule
    )
}