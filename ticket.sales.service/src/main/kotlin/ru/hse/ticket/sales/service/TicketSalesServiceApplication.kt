package ru.hse.ticket.sales.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class TicketSalesServiceApplication

fun main(args: Array<String>) {
	runApplication<TicketSalesServiceApplication>(*args)
}
