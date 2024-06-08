package ru.hse.security.service.client.rest.api

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(name = "ticket-sales-service")
interface TicketSalesServiceApi {
    @PostMapping("/order/create")
    fun createOrder(
        @RequestParam("userId") userId: UUID,
        @RequestParam("fromStation") fromStation: String,
        @RequestParam("toStation") toStation: String
    ): ResponseEntity<String>

    @GetMapping("/order/getOrders")
    fun getOrders(
        @RequestParam("userId") userId: UUID
    ): ResponseEntity<List<String>>

    @GetMapping("/order/showOrder")
    fun getOrder(
        @RequestParam("orderId") orderId: UUID
    ): ResponseEntity<String>
}