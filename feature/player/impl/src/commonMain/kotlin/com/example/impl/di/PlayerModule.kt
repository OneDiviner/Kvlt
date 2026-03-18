package com.example.impl.di

import com.example.impl.presentation.PlayerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playerModule = module {
    includes(
        playerControllerModule
    )
    viewModel {
        PlayerViewModel(playerController = get())
    }
    viewModelOf(::PlayerViewModel)
}