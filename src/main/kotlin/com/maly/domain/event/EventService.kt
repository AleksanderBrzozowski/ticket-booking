package com.maly.domain.event

import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */

@Service
class EventService(private val eventRepository: EventRepository) {

    fun getEventsByPlayId(playId: Long): List<Event> {
        return Specifications.where(EventSpecification.forPlay(playId))
                .let { eventRepository.findAll(it) }
    }

}