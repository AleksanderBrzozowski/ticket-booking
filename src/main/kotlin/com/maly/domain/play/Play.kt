package com.maly.domain.play

import com.maly.domain.event.Event
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "plays")
class Play(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        val description: String,
        @OneToMany(mappedBy = "play")
        val events: Set<Event>
)
