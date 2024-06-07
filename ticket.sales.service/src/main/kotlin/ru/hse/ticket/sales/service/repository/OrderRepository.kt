package ru.hse.ticket.sales.service.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import ru.hse.ticket.sales.service.model.Order
import java.util.UUID

interface OrderRepository : CrudRepository<Order, UUID> {
    @Query(
        """
            SELECT * 
            FROM order_table 
            WHERE status = :status
        """
    )
    fun findAllByStatus(status: Int): List<Order>

    @Query(
        """
            SELECT * 
            FROM order_table 
            WHERE user_id = :userId
        """
    )
    fun findAllByUserId(userId: UUID): List<Order>
}
