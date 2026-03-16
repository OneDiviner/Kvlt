package com.example.kvlt.di

import com.example.impl.di.contentResolverModule
import org.koin.dsl.module

actual val platformModule = module {
    includes(
        contentResolverModule
    )
}