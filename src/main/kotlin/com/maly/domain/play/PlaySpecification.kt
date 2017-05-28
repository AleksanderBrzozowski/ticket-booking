package com.maly.domain.play

import com.maly.domain.building.Building
import com.maly.domain.event.Event
import com.maly.domain.room.Room
import org.springframework.data.jpa.domain.Specification

/**
 * @author Aleksander Brzozowski
 */
class PlaySpecification private constructor(){

    companion object {
        fun forBuilding(buildingId: Long, distinctPlays: Boolean): Specification<Play> {
            return Specification { root, query, cb ->
                query.distinct(distinctPlays)
                val events = root.joinSet<Play, Event>("events")
                val room = events.get<Room>("room")
                val building = room.get<Building>("building")
                cb.equal(building.get<Long>("id"), buildingId)
            }
        }
    }
}