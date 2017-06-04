package com.maly.domain.building

import com.maly.domain.building.BuildingSpecification.Companion.forDistinct
import com.maly.domain.building.BuildingSpecification.Companion.forPlay
import com.maly.presentation.error.BusinessException
import org.springframework.data.jpa.domain.Specifications
import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */

@Service
class BuildingService(private val buildingRepository: BuildingRepository,
                      private val buildingTypeRepository: BuildingTypeRepository) {

    fun findAllBuildings(type: BuildingType.Type): List<Building> {
        return buildingTypeRepository.findOneByName(type.typeName)
                ?.let { buildingRepository.findAllByType(it) }
                ?: throw BusinessException("buildingType.notFound.error")
    }

    fun findAllBuildingsByPlayId(playId: Long): List<Building> {
        val specifications = Specifications.where(forPlay(playId))
                .and(forDistinct())
        return buildingRepository.findAll(specifications)
    }
}