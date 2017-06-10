package com.maly.presentation.order

import com.maly.domain.order.OrderService
import com.maly.system.Api
import org.springframework.web.bind.annotation.*

/**
 * @author Aleksander Brzozowski
 */

@RestController
@RequestMapping("${Api.BASE_URL}/orders")
class OrderController(private val orderService: OrderService) {

    @GetMapping("/discounts")
    fun getDiscounts() = orderService.getDiscounts()

    @PostMapping("/reserve")
    fun reserve(@RequestBody model: ReservationModel) {
        orderService.reserveEvent(model)
    }

    @PostMapping("/buy")
    fun buy(@RequestBody model: BuyModel){
        orderService.buyEvent(model)
    }
}