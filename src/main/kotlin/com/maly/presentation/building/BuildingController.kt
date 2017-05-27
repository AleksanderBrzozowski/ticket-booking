package com.maly.presentation.building

import com.maly.domain.building.BuildingService
import com.maly.domain.building.BuildingType
import com.maly.domain.play.PlayService
import com.maly.presentation.building.BuildingController.Companion.BASE_PATH
import com.maly.presentation.play.PlayModel
import com.maly.system.Api
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}$BASE_PATH")
class BuildingController(private val buildingService: BuildingService,
                         private val playService: PlayService) {

    companion object {
        const val BASE_PATH = "/buildings"
        const val CITY_SORTED_PATH = "/city-sorted"
        const val ID_PATH = "/{id}"
        const val BUILDING_PLAYS = "$ID_PATH/plays"
    }

    @RequestMapping(CITY_SORTED_PATH)
    fun getBuildings(@RequestParam type: BuildingType.Type): List<CitySortedBuildingsModel> {
        return buildingService.findAllBuildings(type)
                .groupBy { it.address.city }
                .map { (city, buildings) ->
                    CitySortedBuildingsModel(city = city, buildings = buildings.map { BuildingModel.map(it) })
                }
    }

    @RequestMapping(BUILDING_PLAYS)
    fun getPlays(@PathVariable id: Long): List<PlayModel> {
        return playService.getPlaysByBuildingId(id)
                .map { PlayModel.of(it) }
    }
}