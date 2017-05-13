package com.maly.domain.address

import javax.persistence.Embeddable

/**
 * @author Aleksander Brzozowski
 */

@Embeddable
class Address(
        val city: String,
        val street: String,
        val houseNumber: String,
        val x: String,
        val y: String
)