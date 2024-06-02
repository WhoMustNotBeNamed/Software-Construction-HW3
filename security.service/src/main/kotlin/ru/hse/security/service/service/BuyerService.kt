package ru.hse.security.service.service

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.hse.security.service.model.AuthenticationRequest
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
    fun registerNewUser(user: Buyer): HttpEntity<String> {
        if (defaultUserRepository.findByUsername(user.username) != null) {
            throw RuntimeException("User already exists")
        }

        val encodedPassword = passwordEncoder.encode(user.password)
        defaultUserRepository.save(
            Buyer(
                username = user.username,
                email =  user.getEmail(),
                password = encodedPassword,
            )
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("User registered successfully")
    }

    fun login(authenticationRequest: AuthenticationRequest): ResponseEntity<String> {
        val userDetails: UserDetails =
            defaultUserDetailsService.loadUserByUsername(authenticationRequest.username)
        val jwt = jwtService.generateToken(userDetails)

        return ResponseEntity.ok(jwt)
    }
}