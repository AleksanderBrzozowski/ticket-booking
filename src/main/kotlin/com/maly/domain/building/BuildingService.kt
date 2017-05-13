package com.maly.domain.building

import com.maly.domain.building.dto.TypeBuildingDto
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */

@Service
class BuildingService(private val buildingRepository: BuildingRepository) {

    fun findAllGroupedByType(): TypeBuildingDto {
        val buildingsMap = buildingRepository.findAll()
                .groupBy { it.type }

        val cinemas = buildingsMap[Building.Type.CINEMA] ?: emptyList()
        val theatres = buildingsMap[Building.Type.THEATRE] ?: emptyList()

        return TypeBuildingDto(theatres = theatres, cinemas = cinemas)
    }

    fun findAllBuildings(type: Building.Type): List<Building> {
        return buildingRepository.findAllByType(type)
    }
}