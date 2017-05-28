package com.maly.presentation.event

import com.maly.domain.event.Event

/**
 * @author Aleksander Brzozowski
 */
class CityGroupEventsModel(val city: String, val buildings: List<BuildingGroupEventsModel>) {

    companion object {
        fun of(events: List<Event>): List<CityGroupEventsModel> {
            return events.groupBy { it.room.building.address.city }
                    .mapValues { (_, events) -> BuildingGroupEventsModel.of(events) }
                    .map { (city, buildingGroupEvents) ->
                        CityGroupEventsModel(city = city, buildings = buildingGroupEvents)
                    }
        }
    }
}