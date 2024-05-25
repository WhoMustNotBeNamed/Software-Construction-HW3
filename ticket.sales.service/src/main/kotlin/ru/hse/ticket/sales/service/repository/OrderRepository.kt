package ru.hse.ticket.sales.service.repository

import org.springframework.data.repository.CrudRepository
import ru.hse.ticket.sales.service.model.Order
import java.util.UUID

interface OrderRepository : CrudRepository<Order, UUID>
