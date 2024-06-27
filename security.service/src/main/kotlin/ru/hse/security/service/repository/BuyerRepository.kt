package ru.hse.security.service.repository

import ru.hse.security.service.model.Buyer
import org.springframework.data.repository.CrudRepository
import java.util.*

interface BuyerRepository : CrudRepository<Buyer, UUID> {
    fun findByUsername(name: String): Buyer?
}