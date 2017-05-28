package com.maly.domain.order

import org.springframework.stereotype.Service

/**
 * @author Aleksander Brzozowski
 */

@Service
class OrderService(private val discountRepository: DiscountRepository,
                   private val saleFormRepository: SaleFormRepository) {

    fun getDiscounts(): List<Discount> = discountRepository.findAll()
}