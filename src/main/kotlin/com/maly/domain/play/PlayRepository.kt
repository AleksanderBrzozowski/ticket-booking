package com.maly.domain.play

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author Aleksander Brzozowski
 */
interface PlayRepository : JpaRepository<Play, Long>, JpaSpecificationExecutor<Play>{
    fun findAllByNameIgnoreCaseStartsWith(name: String): List<Play>
}