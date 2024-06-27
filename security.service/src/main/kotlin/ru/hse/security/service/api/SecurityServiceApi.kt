package ru.hse.security.service.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "Security service ", description = "API для авторизации и аутентификации")
interface SecurityServiceApi {
    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "200",
        ),
        ApiResponse(
            description = "Некорректный запрос",
            responseCode = "400",
        )
    )
    fun register(
        @Schema(
            description = "Имя пользователя",
            defaultValue = "user"
        )
        @NotBlank
        username: String = "user",

        @Schema(
            description = "Email пользователя",
            defaultValue = "user@mail.ru"
        )
        @RequestParam @Pattern(
            message = "Email is not valid",
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
        )
        email: String = "user@mail.com",

        @Schema(
            description = "Пароль пользователя",
            defaultValue = "123"
        )
        @NotBlank
        password: String = "123"
    ): ResponseEntity<String>

    @Operation(summary = "Авторизация пользователя (выдача JWT токена)")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "200",
        ),
        ApiResponse(
            description = "Некорректный запрос",
            responseCode = "400",
        )
    )
    fun login(
        @Schema(
            description = "Имя пользователя",
            defaultValue = "user"
        )
        @NotBlank
        username: String = "user",

        @Schema(
            description = "Пароль пользователя",
            defaultValue = "123"
        )
        @NotBlank
        password: String = "123"
    ): ResponseEntity<String>

    @Operation(summary = "Получение информации о пользователе")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "200",
        ),
        ApiResponse(
            description = "Некорректный запрос",
            responseCode = "400",
        )
    )
    fun getUserInfo(): String
}