package com.maly.domain.building

import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */

@Service
class BuildingService(private val buildingRepository: BuildingRepository,
                      private val buildingTypeRepository: BuildingTypeRepository) {

    fun findAllBuildings(type: BuildingType.Type): List<Building> {
        return buildingTypeRepository.findOneByName(type.name)
                ?.let { buildingRepository.findAllByType(it) }
                ?: throw RuntimeException("buildingType.notFound.error")
    }
}