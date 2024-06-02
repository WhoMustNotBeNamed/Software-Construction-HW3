package ru.hse.ticket.sales.service.scheduler

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import ru.hse.ticket.sales.service.repository.OrderRepository

@Component
class OrderProcessorScheduler (
    private val orderRepository: OrderRepository
){
    @Scheduled(fixedRate = 1000)
    fun processOrders() {
        val orders = orderRepository.findAllByStatus(1)
        orders.forEach { order ->
            Thread.sleep(1000)
            val random = (0..1).random()
            if (random == 0) {
                orderRepository.save(order.apply {
                    status = 3
                })
            } else {
               orderRepository.save(order.apply {
                    status = 2
                })
            }
        }
    }
}