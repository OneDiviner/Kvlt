package com.example.api

import kotlinx.serialization.Serializable

@Serializable
data class Track(
    val id: String,
    val uri: String,
    val title: String? = null,
    val artist: String? = null,
    val genreList: List<Genre>? = null,
    val duration: Long? = null,
    val albumTitle: String? = null,
    val albumArtUri: String? = null
)