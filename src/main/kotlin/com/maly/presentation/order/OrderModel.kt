package com.maly.presentation.order

import com.maly.domain.order.dto.TicketDto
import com.maly.extension.toPolishDate
import com.maly.presentation.room.SeatModel
import java.math.BigDecimal

/**
 * @author Aleksander Brzozowski
 */
class OrderModel(
        val seats: List<SeatModel>,
        val expireDate: String?,
        val eventDate: String,
        val totalPrice: BigDecimal
) {
    companion object {
        fun of(ticketDto: TicketDto): OrderModel {
            return OrderModel(
                    seats = ticketDto.seats.map { SeatModel(it.id, it.row, it.number) },
                    expireDate = ticketDto.expireDate?.toPolishDate(),
                    eventDate = ticketDto.eventDate.toPolishDate(),
                    totalPrice = ticketDto.totalPrice
            )
        }
    }
}