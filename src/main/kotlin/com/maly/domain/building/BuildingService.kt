package com.maly.domain.building

import com.maly.presentation.error.BusinessException
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
                ?: throw BusinessException("buildingType.notFound.error")
    }
}