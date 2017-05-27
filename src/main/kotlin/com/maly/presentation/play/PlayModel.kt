package com.maly.presentation.play

import com.maly.domain.play.Play
import com.maly.presentation.event.EventModel

/**
 * @author Aleksander Brzozowski
 */
class PlayModel private constructor(val name: String, val description: String, val events: List<EventModel>) {

    companion object {
        fun of(play: Play) = PlayModel(
                name = play.name, description = play.description,
                events = play.events.map { EventModel.withoutPlay(it) }
        )
    }
}
