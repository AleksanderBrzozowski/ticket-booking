package com.maly.domain.order

import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "sale_forms")
class SaleForm(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Short,
        val name: String
)