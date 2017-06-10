package com.maly.domain.order

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Aleksander Brzozowski
 */
interface ReservationRepository: JpaRepository<Reservation, Long>