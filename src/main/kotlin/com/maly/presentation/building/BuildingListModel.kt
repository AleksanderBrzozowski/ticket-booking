package com.maly.presentation.building

import com.maly.domain.building.Building

/**
 * @author Aleksander Brzozowski
 */

class BuildingListModel private constructor(
        val cinemas: List<CitySortedBuildings>,
        val theatres: List<CitySortedBuildings>
) {
    companion object {
        fun map(cinemas: List<Building>, theatres: List<Building>): BuildingListModel {
            return BuildingListModel(
                    cinemas = mapBuildings(cinemas),
                    theatres = mapBuildings(theatres)
            )
        }

        private fun mapBuildings(buildings: List<Building>): List<CitySortedBuildings> {
            return buildings.groupBy { it.address.city }
                    .map { (city, buildings) ->
                        CitySortedBuildings(city = city, buildings = buildings.map { BaseBuildingModel.map(it) })
                    }
        }
    }
}

private class CitySortedBuildings(
        val city: String,
        val buildings: List<BaseBuildingModel>
)