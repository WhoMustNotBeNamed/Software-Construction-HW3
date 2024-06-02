package ru.hse.ticket.sales.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.hse.ticket.sales.service.model.Order.Companion.TABLE_NAME
import java.time.LocalDateTime
import java.util.UUID

@Table(TABLE_NAME)
data class Order(
    @Id
    @Column(ID_COLUMN_NAME)
    val id: UUID,

    @Column(USER_ID_COLUMN_NAME)
    val userId: UUID,

    @Column(FROM_STATION_ID_COLUMN_NAME)
    val fromStationId: UUID,

    @Column(TO_STATION_ID_COLUMN_NAME)
    val toStationId: UUID,

    @Column(STATUS_COLUMN_NAME)
    var status: Int,

    @Column(CREATED_COLUMN_NAME)
    val created: LocalDateTime,
) {
    companion object {
        const val TABLE_NAME = "order_table"

        const val ID_COLUMN_NAME = "id"
        const val USER_ID_COLUMN_NAME = "user_id"
        const val FROM_STATION_ID_COLUMN_NAME = "from_station_id"
        const val TO_STATION_ID_COLUMN_NAME = "to_station_id"
        const val STATUS_COLUMN_NAME = "status"
        const val CREATED_COLUMN_NAME = "created"
    }
}
