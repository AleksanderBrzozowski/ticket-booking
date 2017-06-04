package com.maly.domain.order

import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "discounts")
class Discount(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val name: String,
        val value: Int
)