package com.maly.presentation.building

import com.maly.domain.building.BuildingService
import com.maly.presentation.building.BuildingController.Companion.BASE_PATH
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping(BASE_PATH)
class BuildingController(private val buildingService: BuildingService) {

    companion object {
        const val BASE_PATH = "/buildings"
        const val CITY_SORTED_PATH = "/city-sorted"
    }

    @RequestMapping(CITY_SORTED_PATH)
    fun citySortedBuildings(): BuildingListModel {
        return buildingService.findAllGroupedByType()
                .let { BuildingListModel.map(cinemas = it.cinemas, theatres = it.theatres) }
    }
}