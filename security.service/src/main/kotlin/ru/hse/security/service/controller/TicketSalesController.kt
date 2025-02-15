package ru.hse.security.service.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.security.service.client.rest.api.TicketSalesServiceApi
import ru.hse.security.service.repository.BuyerRepository
import ru.hse.security.service.api.TicketSalesApi
import java.util.*

@RestController
@RequestMapping("/ticketApi")
class TicketSalesController(
    private val ticketSalesService: TicketSalesServiceApi,
    private val buyerRepository: BuyerRepository
) : TicketSalesApi {

    @PostMapping("/createOrder")
    override fun createOrder(
        @RequestParam fromStation: String,
        @RequestParam toStation: String
    ): ResponseEntity<String> {
        return try {
            if (SecurityContextHolder.getContext().authentication.name == "anonymousUser") {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login to create order")
            }

            ticketSalesService.createOrder(
                buyerRepository.findByUsername(
                    SecurityContextHolder.getContext().authentication.name
                )!!.id!!,
                fromStation,
                toStation
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message?.split('[')?.last()?.split(']')?.first())
        }
    }

    @GetMapping("/getOrders")
    override fun getOrders(): ResponseEntity<List<String>> {
        return try {
            if (SecurityContextHolder.getContext().authentication.name == "anonymousUser") {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listOf("Login to create order"))
            }

            ticketSalesService.getOrders(
                buyerRepository.findByUsername(
                    SecurityContextHolder.getContext().authentication.name
                )?.id!!
            )
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listOf(e.message!!))
        }
    }

    @GetMapping("/showOrder")
    override fun getOrder(@RequestParam orderId: String): ResponseEntity<String> {
        return try {
            ticketSalesService.getOrder(UUID.fromString(orderId))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message?.split('[')?.last()?.split(']')?.first())
        }
    }
}
