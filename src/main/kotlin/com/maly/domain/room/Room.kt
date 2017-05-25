package com.maly.domain.room

import com.maly.domain.building.Building
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "rooms")
class Room(
        @GeneratedValue
        @Id
        val id: Long,
        val name: String,
        val number: Int,
        @ManyToOne
        val building: Building
)