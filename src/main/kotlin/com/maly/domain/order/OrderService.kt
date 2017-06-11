package com.maly.domain.order

import com.maly.domain.event.EventService
import com.maly.domain.order.dto.TicketDto
import com.maly.domain.room.Seat
import com.maly.domain.room.SeatRepository
import com.maly.extension.getDefaultMessage
import com.maly.presentation.error.BusinessException
import com.maly.presentation.order.BuyModel
import com.maly.presentation.order.ReservationModel
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * @author Aleksander Brzozowski
 */

@Service
class OrderService(private val discountRepository: DiscountRepository,
                   private val saleFormRepository: SaleFormRepository,
                   private val reservationRepository: ReservationRepository,
                   private val seatRepository: SeatRepository,
                   private val eventService: EventService,
                   private val ticketRepository: TicketRepository,
                   private val messageSource: MessageSource,
                   private val saleRepository: SaleRepository) {

    companion object {
        const val SALE_FORM_NAME = "Internetowa"
        const val EXPIRE_TIME_MINUTES = 30L
    }

    fun getDiscounts(): List<Discount> = discountRepository.findAll()

    @Transactional
    fun reserveEvent(model: ReservationModel): TicketDto {
        val saleForm = getSaleForm()

        val now = LocalDateTime.now()
        val event = eventService.getEvent(model.eventId)

        val reservation = Reservation(saleForm = saleForm, expiryDate = event.date.minusMinutes(EXPIRE_TIME_MINUTES),
                date = now, firstName = model.firstName, lastName = model.lastName, telephone = model.telephone)
                .let { reservationRepository.save(it) }

        return model.tickets.map { it.discountId?.let { getDiscountById(it) } to getSeatById(it.seatId) }
                .map { (discount, seat) -> Ticket(reservation = reservation, seat = seat, discount = discount, event = event) }
                .map { saveTicket(it) }
                .let { TicketDto.of(it) }
    }

    fun buyEvent(model: BuyModel): TicketDto {
        val saleForm = getSaleForm()

        val sale = Sale(saleForm = saleForm).let { saleRepository.save(it) }

        val event = eventService.getEvent(model.eventId)
        return model.tickets.map { it.discountId?.let { getDiscountById(it) } to getSeatById(it.seatId) }
                .map { (discount, seat) -> Ticket(sale = sale, seat = seat, discount = discount, event = event) }
                .map { saveTicket(it) }
                .let { TicketDto.of(it) }
    }

    private fun getSaleForm() = (saleFormRepository.findByName(SALE_FORM_NAME)
            ?: throw RuntimeException("SaleForm [$SALE_FORM_NAME] not found"))

    private fun getDiscountById(id: Long): Discount {
        return discountRepository.findOne(id) ?: throw RuntimeException("discount with id: [$id] not found")
    }

    private fun getSeatById(id: Long): Seat {
        return seatRepository.findOne(id) ?: throw RuntimeException("seat with id: [$id] not found")
    }

    private fun saveTicket(ticket: Ticket): Ticket {
        ticketRepository.findBySeatAndEvent(seat = ticket.seat, event = ticket.event)
                ?.let { ticket.seat.let { arrayOf(it.row.toString(), it.number.toString()) } }
                ?.let { messageSource.getDefaultMessage("seat", it) }
                ?.apply {
                    throw BusinessException(
                            messageCode = "seat.notFree.error",
                            params = arrayOf(this),
                            systemMessage = "Seat with id: ${ticket.seat.id} not free for event id: ${ticket.event.id}"
                    )
                }

        return ticketRepository.save(ticket)
    }
}