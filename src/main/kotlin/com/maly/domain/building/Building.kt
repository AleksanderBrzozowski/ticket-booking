package com.maly.domain.building

import com.maly.domain.address.Address
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "buildings")
class Building(
        @GeneratedValue
        @Id
        val id: Long,
        @Embedded
        val address: Address,
        @Enumerated(EnumType.STRING)
        val type: Type,
        val name: String
){
    enum class Type {
        CINEMA, THEATRE
    }
}