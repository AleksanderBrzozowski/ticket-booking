package com.maly.domain.event

import com.maly.domain.building.Building
import com.maly.domain.play.Play
import com.maly.domain.room.Room
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime

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

        fun forFutureDate(): Specification<Event> {
            return Specification { root, _, cb ->
                val date = root.get<LocalDateTime>("date")
                cb.greaterThan(date, LocalDateTime.now())
            }
        }

        fun forBuilding(buildingId: Long): Specification<Event> {
            return Specification { root, _, cb ->
                val building = root.get<Room>("room").get<Building>("building")
                cb.equal(building.get<Long>("id"), buildingId)
            }
        }
    }

}