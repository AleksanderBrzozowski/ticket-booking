package com.maly.domain.event

import com.maly.domain.event.EventSpecification.Companion.forBuilding
import com.maly.domain.event.EventSpecification.Companion.forFutureDate
import com.maly.domain.event.EventSpecification.Companion.forPlay
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

    fun getEvents(playId: Long, buildingId: Long): List<Event> {
        return Specifications.where(forPlay(playId))
                .and(forBuilding(buildingId))
                .and(forFutureDate())
                .let { eventRepository.findAll(it) }
    }

    fun getEvent(eventId: Long): Event{
        return eventRepository.findOne(eventId) ?: throw RuntimeException("Event with id [$eventId] not found")
    }
}