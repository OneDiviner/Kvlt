package com.example.api

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: String,
    val name: String
)