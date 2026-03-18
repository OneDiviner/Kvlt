package com.example.impl.di

import com.example.api.PlayerController
import com.example.impl.PlayerControllerImpl
import org.koin.dsl.module

val playerControllerModule = module {
    single<PlayerController> {
        PlayerControllerImpl()
    }
}