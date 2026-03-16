package com.example.impl.data

data class Track(
    val id: String,
    val uri: String,
    val title: String? = null,
    val artist: String? = null,
    val genreList: List<Genre>? = null,
    val duration: Long? = null,
    val albumTitle: String? = null,
    val artworkUri: String? = null
)