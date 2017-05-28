package com.maly.domain.event

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

/**
 * @author Aleksander Brzozowski
 */
interface EventRepository: JpaRepository<Event, Long>, JpaSpecificationExecutor<Event>