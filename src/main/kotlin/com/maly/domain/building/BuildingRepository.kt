package com.maly.domain.building

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Aleksander Brzozowski
 */
interface BuildingRepository: JpaRepository<Building, Long> {
    fun findAllByType(type: Building.Type): List<Building>
}