package com.maly.domain.building

import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "building_types")
class BuildingType(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        val name: String
) {
    enum class Type(val typeName: String) {
        CINEMA("Kino"), THEATRE("Teatr")
    }
}