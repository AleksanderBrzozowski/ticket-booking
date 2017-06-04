package com.maly.domain.building

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author Aleksander Brzozowski
 */
interface BuildingRepository: JpaRepository<Building, Long>, JpaSpecificationExecutor<Building> {
    fun findAllByType(type: BuildingType): List<Building>
}