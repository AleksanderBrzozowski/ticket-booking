package com.maly.presentation.order

import com.maly.system.NoArgConstructor

/**
 * @author Aleksander Brzozowski
 */

@NoArgConstructor
class TicketModel(
        val seatId: Long,
        val discountId: Long?
)