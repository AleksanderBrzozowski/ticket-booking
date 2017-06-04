package com.maly.domain.ticket

import com.maly.domain.event.Event
import com.maly.domain.order.Discount
import com.maly.domain.order.SaleForm
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
        val id: Long,
        @ManyToOne
        val discount: Discount,
        @ManyToOne
        val sale: SaleForm,
        @ManyToOne
        @JoinColumn(name = "seating_id")
        val seat: Seat,
        @ManyToOne
        val event: Event
)