package ru.hse.ticket.sales.service.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.ticket.sales.service.api.TicketSalesServiceApi
import ru.hse.ticket.sales.service.repository.StationRepository
import ru.hse.ticket.sales.service.service.OrderService
import java.util.UUID

@RestController
@RequestMapping("/order")
class OrderController(
    val orderService: OrderService,
    val stationRepository: StationRepository
) : TicketSalesServiceApi {
    @PostMapping("/create")
    override fun createOrder(
        @RequestParam  userId: String,
        @RequestParam fromStation: String,
        @RequestParam toStation: String
    ): ResponseEntity<Any> {
        return try {
            orderService.createOrder(UUID.fromString(userId), fromStation, toStation)
            ResponseEntity.status(HttpStatus.CREATED).body("Order created")
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/getOrders")
    override fun getOrders(@RequestParam userId: UUID): ResponseEntity<List<String>> {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(userId).map {
            "Заказ ${it.id}"
        })
    }

    @GetMapping("/showOrder")
    override fun getOrder(
        @RequestParam orderId: String
    ): ResponseEntity<String> {
        return try {
            val order = orderService.getOrder(UUID.fromString(orderId))
            val response = "Заказ ${order.id}:\n" +
                    "Станция отправления: ${stationRepository.findById(order.fromStationId).get().station}\n" +
                    "Станция прибытия: ${stationRepository.findById(order.toStationId).get().station}\n" +
                    "Статус: ${order.status}"
            ResponseEntity.status(HttpStatus.OK).body(response)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong order id")
        }
    }
}