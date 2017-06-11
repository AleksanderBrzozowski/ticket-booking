package com.maly.presentation.event

import com.maly.domain.event.Event
import com.maly.extension.toPolishDate
import com.maly.presentation.room.RoomModel
import java.math.BigDecimal

/**
 * @author Aleksander Brzozowski
 */
class EventModel(val id: Long, val date: String, val room: RoomModel, val price: BigDecimal) {

    companion object {
        fun of(event: Event) = EventModel(
                id = event.id, date = event.date.toPolishDate(),
                price = event.price, room = event.room.let { RoomModel.of(it) }
        )
    }
}