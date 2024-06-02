package ru.hse.security.service.model

data class AuthenticationRequest(
    val username: String,
    val password: String
)