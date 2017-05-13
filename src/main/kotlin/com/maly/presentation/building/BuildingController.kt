package com.maly.presentation.building

import com.maly.domain.building.Building
import com.maly.domain.building.BuildingService
import com.maly.presentation.building.BuildingController.Companion.BASE_PATH
import com.maly.system.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}$BASE_PATH")
class BuildingController(private val buildingService: BuildingService) {

    companion object {
        const val BASE_PATH = "/buildings"
        const val CITY_SORTED_PATH = "/city-sorted"
    }

    @RequestMapping(CITY_SORTED_PATH)
    fun getBuildings(@RequestParam type: Building.Type): List<CitySortedBuildingsModel> {
        return buildingService.findAllBuildings(type)
                .groupBy { it.address.city }
                .map { (city, buildings) ->
                    CitySortedBuildingsModel(city = city, buildings = buildings.map { BuildingModel.map(it) })
                }
    }
}