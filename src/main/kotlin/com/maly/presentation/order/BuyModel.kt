package com.maly.presentation.order

import com.maly.system.NoArgConstructor

/**
 * @author Aleksander Brzozowski
 */

@NoArgConstructor
class BuyModel (
        val eventId: Long,
        val tickets: List<TicketModel>
)