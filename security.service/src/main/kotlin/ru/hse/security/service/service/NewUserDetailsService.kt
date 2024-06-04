package ru.hse.security.service.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.hse.security.service.repository.BuyerRepository

@Service
class NewUserDetailsService(
    private val buyerRepository: BuyerRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        buyerRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User $username not found")
}