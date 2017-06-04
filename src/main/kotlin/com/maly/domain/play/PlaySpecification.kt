package com.maly.domain.play

import com.maly.domain.building.Building
import com.maly.domain.event.Event
import com.maly.domain.room.Room
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

/**
 * @author Aleksander Brzozowski
 */
class PlaySpecification private constructor(){

    companion object {
        fun forBuilding(buildingId: Long): Specification<Play> {
            return Specification { root, _, cb ->
                val events = root.joinSet<Play, Event>("events")
                val room = events.get<Room>("room")
                val building = room.get<Building>("building")
                cb.equal(building.get<Long>("id"), buildingId)
            }
        }

        fun forFutureDate(): Specification<Play> {
            return Specification { root, _, cb ->
                val events = root.joinSet<Play, Event>("events")
                val eventDate = events.get<LocalDateTime>("date")
                cb.greaterThan(eventDate, LocalDateTime.now())
            }
        }

        fun forTitle(title: String): Specification<Play> {
            return Specification { root, _, cb ->
                val playTitle = root.get<String>("name")
                cb.like(cb.lower(playTitle), "$title%")
            }
        }

        fun forDistinct(): Specification<Play> {
            return Specification { root, query, cb ->
                query.distinct(true)
                //TODO workaround to query distinct
                cb.equal(root.get<Long>("id"), root.get<Long>("id"))
            }
        }
    }
}