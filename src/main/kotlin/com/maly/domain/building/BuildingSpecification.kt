package com.maly.domain.building

import com.maly.domain.event.Event
import com.maly.domain.play.Play
import com.maly.domain.room.Room
import org.springframework.data.jpa.domain.Specification

/**
 * @author Aleksander Brzozowski
 */
class BuildingSpecification private constructor() {

    companion object {
        fun forPlay(playId: Long): Specification<Building> {
            return Specification { root, _, cb ->
                val play = root.joinSet<Building, Room>("rooms").joinSet<Room, Event>("events").get<Play>("play")
                cb.equal(play.get<Long>("id"), playId)
            }
        }

        fun forDistinct(): Specification<Building> {
            return Specification { root, query, cb ->
                //TODO workaround
                query.distinct(true)
                cb.equal(root.get<Long>("id"), root.get<Long>("id"))
            }
        }
    }
}