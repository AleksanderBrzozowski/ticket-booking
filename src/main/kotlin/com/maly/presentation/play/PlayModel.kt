package com.maly.presentation.play

import com.maly.domain.play.Play

/**
 * @author Aleksander Brzozowski
 */
class PlayModel private constructor(val id: Long, val name: String, val description: String) {

    companion object {
        fun of(play: Play) = PlayModel(
                id = play.id, name = play.name, description = play.description
        )
    }
}
