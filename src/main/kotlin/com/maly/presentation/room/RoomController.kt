package com.maly.presentation.room

import com.maly.domain.room.RoomService
import com.maly.presentation.room.RoomController.Companion.BASE_PATH
import com.maly.system.Api
import org.springframework.web.bind.annotation.*

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}/$BASE_PATH")
class RoomController(private val roomService: RoomService) {

    companion object {
        const val BASE_PATH = "/rooms"
        const val ID_PATH = "/{id}"
        const val SEATS = "$ID_PATH/seats"
    }

    @GetMapping(SEATS)
    fun getSeats(@PathVariable id: Long, @RequestParam eventId: Long): List<SeatModel> {
        return roomService.findSeats(id, eventId)
                .map { SeatModel(it.id, it.row, it.number) }
    }
}