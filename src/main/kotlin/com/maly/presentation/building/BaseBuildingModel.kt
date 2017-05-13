package com.maly.presentation.building

import com.maly.domain.building.Building

/**
 * @author Aleksander Brzozowski
 */
class BaseBuildingModel private constructor(
        val name: String
) {
    companion object {
        fun map(building: Building): BaseBuildingModel {
            return BaseBuildingModel(name = building.name)
        }
    }
}