package ru.hse.ticket.sales.service.controller

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.ticket.sales.service.api.TicketSalesServiceApi
import ru.hse.ticket.sales.service.service.OrderService
import java.util.UUID

@RestController
@RequestMapping("/order")
class OrderController(
    val orderService: OrderService
) : TicketSalesServiceApi {
    @PostMapping("/create")
    override fun createOrder(
        @RequestParam @NotNull userId: UUID,
        @RequestParam @NotBlank fromStation: String,
        @RequestParam @NotBlank toStation: String
    ): ResponseEntity<Any> {
        return try {
            orderService.createOrder(userId, fromStation, toStation)
            ResponseEntity.status(HttpStatus.CREATED).body("Order created")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/getOrders")
    override fun getOrders(@RequestParam @NotNull userId : UUID): ResponseEntity<List<String>> {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(userId))
    }

    @GetMapping("/showOrder")
    override fun getOrder(@RequestParam @NotNull orderId: UUID): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(orderId))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }
}