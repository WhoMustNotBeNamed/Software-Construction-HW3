package ru.hse.ticket.sales.service.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.hse.ticket.sales.service.model.Order
import ru.hse.ticket.sales.service.repository.OrderRepository
import ru.hse.ticket.sales.service.repository.StationRepository
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrderService(
    val orderRepository: OrderRepository,
    val stationRepository: StationRepository
) {
    fun createOrder(userId: UUID, fromStation: String, toStation: String) {
        if (!stationRepository.existsByStation(fromStation) || !stationRepository.existsByStation(toStation)) {
            throw IllegalArgumentException("Station not found")
        } else if (fromStation == toStation) {
            throw IllegalArgumentException("From and to stations are the same")
        } else if (
            orderRepository.findAll()
                .any {
                    it.userId == userId &&
                            it.fromStationId == stationRepository.findByStation(fromStation) &&
                            it.toStationId == stationRepository.findByStation(toStation)
                }
        ) {
            throw IllegalArgumentException("Order already exists")
        }

        orderRepository.save(
            Order(
                id = UUID.randomUUID(),
                userId = userId,
                fromStationId = stationRepository.findByStation(fromStation),
                toStationId = stationRepository.findByStation(toStation),
                status = 1,
                created = LocalDateTime.now()
            )
        )
    }

    fun getOrders(): List<Order> {
        return orderRepository.findAll().toList()
    }

    fun getOrder(orderId: UUID): Order {
        return orderRepository.findById(orderId).orElseThrow { IllegalArgumentException("Order not found") }
    }
}