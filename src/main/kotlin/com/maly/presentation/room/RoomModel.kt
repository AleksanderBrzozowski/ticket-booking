package com.maly.presentation.room

import com.maly.domain.room.Room

/**
 * @author Aleksander Brzozowski
 */
class RoomModel private constructor(val id: Long, val name: String) {

    companion object {
        fun of(room: Room) : RoomModel{
            val roomName = room.name
                    ?.let { "Sala: $it, nr: ${room.number}" }
                    ?: "Sala nr: ${room.number}"
            return RoomModel(id = room.id, name = roomName)
        }
    }
}