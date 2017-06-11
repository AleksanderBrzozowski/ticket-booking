package com.maly.domain.order

import com.maly.domain.event.Event
import com.maly.domain.room.Seat
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "tickets")
class Ticket(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @ManyToOne
        val reservation: Reservation? = null,
        @ManyToOne
        val discount: Discount?,
        @JoinColumn(name = "seating_id")
        @ManyToOne
        val seat: Seat,
        @ManyToOne
        val sale: Sale? = null,
        @ManyToOne
        val event: Event
)