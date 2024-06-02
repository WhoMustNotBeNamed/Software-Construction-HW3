package ru.hse.ticket.sales.service.controller

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.ticket.sales.service.api.TicketSalesServiceApi
import ru.hse.ticket.sales.service.model.Order
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
            ResponseEntity.ok().body("Order created")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/getOrders")
    override fun getOrders(): ResponseEntity<List<Order>> {
        return ResponseEntity.ok().body(orderService.getOrders())
    }

    @GetMapping("/showOrder")
    override fun getOrder(@RequestParam @NotNull orderId: UUID): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(orderService.getOrder(orderId))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}