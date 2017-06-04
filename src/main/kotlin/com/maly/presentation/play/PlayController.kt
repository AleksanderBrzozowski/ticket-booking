package com.maly.presentation.play

import com.maly.domain.building.BuildingService
import com.maly.domain.play.PlayService
import com.maly.presentation.building.BuildingModel
import com.maly.presentation.building.CitySortedBuildingsModel
import com.maly.presentation.play.PlayController.Companion.BASE_PATH
import com.maly.system.Api
import org.springframework.web.bind.annotation.*

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}/$BASE_PATH")
class PlayController(private val playService: PlayService, private val buildingService: BuildingService) {

    companion object {
        const val BASE_PATH = "/plays"
        const val ID_PATH = "/{playId}"
        const val BUILDINGS = "$ID_PATH/buildings"
    }

    @GetMapping
    fun getPlays(@RequestParam(required = false) query: String?): List<PlayModel> {
        val plays = (query?.let { playService.getPlaysByQuery(it) }
                ?: playService.getPlays())

        return plays.map { PlayModel.of(it) }
    }

    @GetMapping(BUILDINGS)
    fun getBuildings(@PathVariable playId: Long): List<CitySortedBuildingsModel> {
        return buildingService.findAllBuildingsByPlayId(playId)
                .groupBy { it.address.city }
                .map { (city, buildings) ->
                    CitySortedBuildingsModel(city = city, buildings = buildings.map { BuildingModel.map(it) })
                }
    }
}