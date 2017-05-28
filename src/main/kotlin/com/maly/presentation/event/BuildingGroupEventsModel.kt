package com.maly.presentation.event

import com.maly.domain.event.Event
import com.maly.presentation.building.BuildingModel

/**
 * @author Aleksander Brzozowski
 */
class BuildingGroupEventsModel(val building: BuildingModel, val events: List<EventModel>) {

    companion object {
        fun of(events: List<Event>): List<BuildingGroupEventsModel> {
            return events.groupBy { it.room.building }
                    .map { (building, events) ->
                        BuildingGroupEventsModel(
                                building = BuildingModel.map(building),
                                events = events.map { EventModel.of(it) }
                        )
                    }
        }
    }
}