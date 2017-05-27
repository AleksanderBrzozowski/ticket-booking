package com.maly.presentation.event

import com.maly.domain.event.Event
import com.maly.presentation.room.RoomModel
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Aleksander Brzozowski
 */
class EventModel(val id: Long, val date: LocalDateTime, val room: RoomModel, val price: BigDecimal) {

    companion object {
        fun withoutPlay(event: Event) = EventModel(
                id = event.id, date = event.date, price = event.price, room = event.room.let { RoomModel.of(it) }
        )
    }
}