package com.maly.presentation.event

import com.maly.domain.event.Event
import com.maly.presentation.room.RoomModel
import java.math.BigDecimal
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

/**
 * @author Aleksander Brzozowski
 */
class EventModel(val id: Long, val date: String, val room: String, val price: BigDecimal) {

    companion object {
        private val dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
                .withLocale(Locale("PL"))

        fun of(event: Event) = EventModel(
                id = event.id, date = dateTimeFormatter.format(event.date),
                price = event.price, room = event.room.let { RoomModel.of(it) }
        )
    }
}