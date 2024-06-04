package ru.hse.security.service.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.hse.security.service.model.Buyer
import ru.hse.security.service.repository.BuyerRepository
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
@ExperimentalEncodingApi
class BuyerService(
    private val defaultUserDetailsService: NewUserDetailsService,
    private val defaultUserRepository: BuyerRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun registerNewUser(name: String, email: String, password: String): HttpEntity<String> {
        if (defaultUserRepository.findByUsername(name) != null) {
            throw RuntimeException("User already exists")
        }

        val encodedPassword = passwordEncoder.encode(password)
        defaultUserRepository.save(
            Buyer(
                username = name,
                email = email,
                password = encodedPassword,
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("User registered successfully")
    }

    fun login(username: String, password: String): ResponseEntity<String> {
        val userDetails: UserDetails = try {
            defaultUserDetailsService.loadUserByUsername(username)
        } catch (e: Exception) {
            throw RuntimeException("User not found")
        }

        if (!passwordEncoder.matches(password, userDetails.password)) {
            throw Exception("Invalid  password")
        }

        val jwt = jwtService.generateToken(userDetails)
        return ResponseEntity.status(HttpStatus.OK).body(jwt)
    }
}