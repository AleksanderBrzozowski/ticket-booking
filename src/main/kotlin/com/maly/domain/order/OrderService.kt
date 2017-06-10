package com.maly.domain.order

import com.maly.domain.event.EventService
import com.maly.domain.room.SeatRepository
import com.maly.presentation.order.ReservationModel
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
                   private val ticketRepository: TicketRepository) {

    companion object {
        const val SALE_FORM_NAME = "Internetowa"
        const val EXPIRE_TIME_MINUTES = 30L
    }

    fun getDiscounts(): List<Discount> = discountRepository.findAll()

    @Transactional
    fun reserveEvent(model: ReservationModel) {
        val saleForm = saleFormRepository.findByName(SALE_FORM_NAME)
                ?: throw RuntimeException("SaleForm [$SALE_FORM_NAME] not found")

        val now = LocalDateTime.now()
        val reservation = Reservation(saleForm = saleForm, expiryDate = now.plusMinutes(EXPIRE_TIME_MINUTES), date = now,
                firstName = model.firstName, lastName = model.lastName, telephone = model.telephone)
                .let { reservationRepository.save(it) }

        model.tickets.map { it.discountId?.let { discountRepository.findOne(it) } to seatRepository.findOne(it.seatId) }
                .map { (discount, seat) ->
                    Ticket(reservation = reservation, seat = seat, discount = discount,
                            event = eventService.getEvent(model.eventId))
                }.forEach { ticketRepository.save(it) }
    }
}