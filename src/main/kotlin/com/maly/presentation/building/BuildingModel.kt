package com.maly.presentation.building

import com.maly.domain.building.Building

/**
 * @author Aleksander Brzozowski
 */
class BuildingModel private constructor(
        val name: String,
        val id: Long
) {
    companion object {
        fun map(building: Building): BuildingModel {
            return BuildingModel(name = building.name, id = building.id)
        }
    }
}