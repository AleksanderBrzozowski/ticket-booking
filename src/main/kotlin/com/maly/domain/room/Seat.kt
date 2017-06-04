package com.maly.domain.room

import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "seatings")
class Seat(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val row: Int,
        val number: Int,
        @ManyToOne
        val room: Room
)