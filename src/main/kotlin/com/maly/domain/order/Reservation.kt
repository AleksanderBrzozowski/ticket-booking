package com.maly.domain.order

import java.time.LocalDateTime
import javax.persistence.*

/**
 * @author Aleksander Brzozowski
 */

@Entity
@Table(name = "reservations")
class Reservation (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val saleForm: SaleForm,
    val expiryDate: LocalDateTime,
    @Column(name = "firstname")
    val firstName: String,
    @Column(name = "lastname")
    val lastName: String,
    val telephone: String,
    val date: LocalDateTime
)