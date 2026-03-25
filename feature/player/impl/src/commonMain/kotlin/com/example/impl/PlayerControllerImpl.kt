package com.example.impl

import com.example.api.PlaybackState
import com.example.api.PlayerController
import com.example.api.PlayerIntent
import kotlinx.coroutines.flow.StateFlow

expect class PlayerControllerImpl(): PlayerController {

    override val playbackState: StateFlow<PlaybackState>

    override fun handleIntent(playerIntent: PlayerIntent)

}