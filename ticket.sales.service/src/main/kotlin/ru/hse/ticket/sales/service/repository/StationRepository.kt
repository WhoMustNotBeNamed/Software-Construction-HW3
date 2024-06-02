package ru.hse.ticket.sales.service.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.hse.ticket.sales.service.model.Station
import java.util.UUID

@Repository
interface StationRepository : CrudRepository<Station, UUID> {
    @Query(
        """
            SELECT EXISTS(
            SELECT 1 
            FROM station 
            WHERE station = :station
            )
        """
    )
    fun existsByStation(station: String): Boolean

    @Query(
        """
            SELECT id 
            FROM station 
            WHERE station = :station
        """
    )
    fun findByStation(station: String): UUID
}
