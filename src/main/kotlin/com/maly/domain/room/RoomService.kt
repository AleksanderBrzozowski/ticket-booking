package com.maly.domain.room

import com.maly.domain.room.SeatSpecification.Companion.forFree
import com.maly.domain.room.SeatSpecification.Companion.forRoom
import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */

@Service
class RoomService(private val seatRepository: SeatRepository) {

    fun findSeats(roomId: Long, eventId: Long): List<Seat> {
        val specifications = Specifications.where(forRoom(roomId))
                .and(forFree( eventId))

        return seatRepository.findAll(specifications)
    }
}