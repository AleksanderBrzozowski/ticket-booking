package com.maly.domain.room

import com.maly.domain.event.Event
import com.maly.domain.ticket.Ticket
import org.springframework.data.jpa.domain.Specification

/**
 * @author Aleksander Brzozowski
 */
class SeatSpecification private constructor(){

    companion object {

        fun forFree(eventId: Long): Specification<Seat> {
            return Specification { root, query, cb ->
                val seatId = root.get<Long>("id")

                val sq = query.subquery(Long::class.java)
                val ticket = sq.from(Ticket::class.java)

                val seat = ticket.get<Seat>("seat")
                val seatPredicate = cb.equal(seat.get<Long>("id"), seatId)

                val event = ticket.get<Event>("event")
                val eventPredicate = cb.equal(event.get<Long>("id"), eventId)

                sq.where(cb.and(eventPredicate, seatPredicate))
                cb.equal(sq.select(cb.count(ticket)), 0)
            }
        }

        fun forRoom(roomId: Long): Specification<Seat> {
            return Specification { root, _, cb ->
                val room = root.get<Room>("room")
                cb.equal(room.get<Long>("id"), roomId)
            }
        }
    }
}