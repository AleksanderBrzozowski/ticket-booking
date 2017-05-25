package com.maly.domain.building

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Aleksander Brzozowski
 */
interface BuildingTypeRepository: JpaRepository<BuildingType, Int>{
    fun findOneByName(name: String): BuildingType?
}