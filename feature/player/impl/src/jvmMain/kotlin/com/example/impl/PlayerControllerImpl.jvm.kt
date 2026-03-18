package com.example.impl

import com.example.api.PlaybackState
import com.example.api.PlayerController
import com.example.api.Track
import kotlinx.coroutines.flow.StateFlow

actual class PlayerControllerImpl actual constructor() : PlayerController {

    actual override val playbackState: StateFlow<PlaybackState>
        get() = TODO("Not yet implemented")

    actual override fun play(track: Track) {
    }
}