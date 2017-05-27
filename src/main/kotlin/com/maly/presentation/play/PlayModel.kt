package com.maly.presentation.play

import com.maly.domain.play.Play

/**
 * @author Aleksander Brzozowski
 */
class PlayModel private constructor(val name: String, val description: String) {

    companion object {
        fun of(play: Play) = PlayModel(
                name = play.name, description = play.description
        )
    }
}
