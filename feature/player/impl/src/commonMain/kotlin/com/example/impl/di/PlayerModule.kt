package com.example.impl.di

import com.example.impl.presentation.PlayerControllerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playerModule = module {
    includes(
        playerControllerModule
    )
    viewModel {
        PlayerControllerViewModel(playerController = get())
    }
    viewModelOf(::PlayerControllerViewModel)
}