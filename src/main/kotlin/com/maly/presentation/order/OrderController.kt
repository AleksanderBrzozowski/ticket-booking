package com.maly.presentation.order

import com.maly.domain.order.OrderService
import com.maly.system.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}/orders")
class OrderController(private val orderService: OrderService) {

    @GetMapping("/discounts")
    fun getDiscounts() = orderService.getDiscounts()
}