package com.maly.domain.order.dto

import com.maly.domain.order.Ticket
import com.maly.domain.room.Seat
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Aleksander Brzozowski
 */
class TicketDto(
        val seats: List<Seat>,
        val totalPrice: BigDecimal,
        val eventDate: LocalDateTime,
        val expireDate: LocalDateTime?
) {
    companion object {
        fun of(tickets: List<Ticket>): TicketDto {
            val oneHundred = BigDecimal(100)
            val totalPrice = tickets.map { ticket ->
                ticket.discount?.value
                        ?.let { ticket.event.price.multiply(BigDecimal(it)).divide(oneHundred) }
                        ?: ticket.event.price
            }.reduce { price1, price2 -> price1.add(price2) }

            return TicketDto(
                    seats = tickets.map { it.seat },
                    totalPrice = totalPrice,
                    eventDate = tickets[0].event.date,
                    expireDate = tickets[0].reservation?.expiryDate
            )
        }
    }
}