package com.maly.presentation.order

import com.maly.system.NoArgConstructor

/**
 * @author Aleksander Brzozowski
 */
@NoArgConstructor
class ReservationModel(
        val eventId: Long,
        val tickets: List<TicketModel>,
        val firstName: String,
        val lastName: String,
        val telephone: String
)