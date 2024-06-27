package ru.hse.ticket.sales.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.hse.ticket.sales.service.model.Station.Companion.TABLE_NAME
import java.util.UUID

@Table(TABLE_NAME)
data class Station(
    @Id
    @Column(ID_COLUMN_NAME)
    val id: UUID,

    @Column(STATION_COLUMN_NAME)
    val station: String,
) {
    companion object {
        const val TABLE_NAME = "station"

        const val ID_COLUMN_NAME = "id"
        const val STATION_COLUMN_NAME = "station"
    }
}
