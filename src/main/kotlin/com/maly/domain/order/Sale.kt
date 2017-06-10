package com.maly.domain.order

import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "sales")
class Sale(
        @Id
        @GeneratedValue
        val id: Long = 0,
        val date: LocalDateTime = LocalDateTime.now(),
        @ManyToOne
        val saleForm: SaleForm
)