package ru.hse.security.service.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity

@Tag(name = "Security service ", description = "API для авторизации и аутентификации")
interface SecurityServiceApi {
    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "200",
        )
    )
    fun register(
        @Schema(
            description = "Имя пользователя",
            defaultValue = "user"
        )
        username: String = "user",

        @Schema(
            description = "Email пользователя",
            defaultValue = "user@mail.ru"
        )
        email: String = "user@mail.com",

        @Schema(
            description = "Пароль пользователя",
            defaultValue = "123"
        )
        password: String = "123"
    ): HttpEntity<String>

    @Operation(summary = "Авторизация пользователя (выдача JWT токена)")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "200",
        )
    )
    fun login(
        @Schema(
            description = "Имя пользователя",
            defaultValue = "user"
        )
        username: String = "user",

        @Schema(
            description = "Пароль пользователя",
            defaultValue = "123"
        )
        password: String = "123"
    ): ResponseEntity<String>

    @Operation(summary = "Получение информации о пользователе")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "200",
        )
    )
    fun getUserInfo(): String
}