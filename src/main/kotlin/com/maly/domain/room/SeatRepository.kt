package com.maly.domain.room

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author Aleksander Brzozowski
 */
interface SeatRepository: JpaRepository<Seat, Long>, JpaSpecificationExecutor<Seat>