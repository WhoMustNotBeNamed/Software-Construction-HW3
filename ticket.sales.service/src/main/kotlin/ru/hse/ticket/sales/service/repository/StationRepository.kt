package ru.hse.ticket.sales.service.repository

import org.springframework.data.repository.CrudRepository
import ru.hse.ticket.sales.service.model.Station
import java.util.UUID

interface StationRepository : CrudRepository<Station, UUID>
