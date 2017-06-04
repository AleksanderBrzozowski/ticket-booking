package com.maly.presentation.room

import com.maly.domain.room.Room

/**
 * @author Aleksander Brzozowski
 */
class RoomModel private constructor(val id: Long, val name: String, val seats: List<SeatModel>) {

    companion object {
        fun of(room: Room) : RoomModel{
            val roomName = room.name
                    ?.let { "Sala: $it, nr: ${room.number}" }
                    ?: "Sala nr: ${room.number}"
            return RoomModel(
                    id = room.id, name = roomName,
                    seats = room.seats.map { SeatModel(id = it.id, row = it.row, number = it.number) }
            )
        }
    }
}