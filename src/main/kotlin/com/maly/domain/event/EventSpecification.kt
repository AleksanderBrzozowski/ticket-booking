package com.maly.domain.event

import com.maly.domain.play.Play
import org.springframework.data.jpa.domain.Specification

/**
 * @author Aleksander Brzozowski
 */
class EventSpecification private constructor() {

    companion object {
        fun forPlay(playId: Long): Specification<Event> {
            return Specification { root, _, cb ->
                val play = root.get<Play>("play")
                cb.equal(play.get<Long>("id"), playId)
            }
        }
    }

}