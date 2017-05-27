package com.maly.domain.event

import com.maly.domain.play.Play
import com.maly.domain.room.Room
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "events")
class Event(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val date: LocalDateTime,
        @ManyToOne
        val play: Play,
        @ManyToOne
        val room: Room,
        val price: BigDecimal
)