package com.maly

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author Aleksander Brzozowski
 */

@SpringBootApplication
class TicketBookingApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(TicketBookingApplication::class.java, *args)
        }
    }
}