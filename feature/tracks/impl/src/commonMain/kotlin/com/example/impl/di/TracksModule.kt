package com.example.impl.di

import com.example.impl.presentation.TracksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tracksModule = module {
    includes(
        tracksRepositoryModule,
    )
    viewModel {
        TracksViewModel(
            playerController = get(),
            repository = get()
        )
    }
    viewModelOf(::TracksViewModel)
}