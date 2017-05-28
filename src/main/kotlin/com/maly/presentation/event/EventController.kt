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

    @GetMapping("/building-group")
    fun getEvents(@RequestParam playId: Long): List<CityGroupEventsModel> {
        return eventService.getEventsByPlayId(playId)
                .let { CityGroupEventsModel.of(it) }
    }

}