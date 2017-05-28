package com.maly.presentation.play

import com.maly.domain.play.Play
import com.maly.presentation.event.EventModel

/**
 * @author Aleksander Brzozowski
 */
class PlayModel private constructor(val id: Long, val name: String, val description: String, val events: List<EventModel>) {

    companion object {
        fun of(play: Play) = PlayModel(
                id = play.id, name = play.name, description = play.description,
                events = play.events.map { EventModel.of(it) }
        )
    }
}
