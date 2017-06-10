package com.maly.domain.order

import com.maly.domain.event.Event
import com.maly.domain.room.Seat
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Aleksander Brzozowski
 */
interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findBySeatAndEvent(seat: Seat, event: Event): Ticket?
}