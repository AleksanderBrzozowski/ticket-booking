package com.maly.presentation.event

import com.maly.domain.event.EventService
import com.maly.system.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}/events")
class EventController(private val eventService: EventService) {

    @GetMapping
    fun getEvents(@RequestParam buildingId: Long, @RequestParam playId: Long): List<EventModel> {
        return eventService.getEvents(playId = playId, buildingId = buildingId)
                .map { EventModel.of(it) }
    }
}