package com.maly.presentation.room

import com.maly.domain.room.Room

/**
 * @author Aleksander Brzozowski
 */
class RoomModel private constructor(val id: Long, val name: String, val number: Int) {

    companion object {
        fun of(room: Room) = RoomModel(
                id = room.id, name = room.name, number = room.number
        )
    }
}