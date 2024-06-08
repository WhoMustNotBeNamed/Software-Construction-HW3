package ru.hse.security.service.api

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.springframework.http.ResponseEntity
import java.util.*

@Tag(name = "Ticket sales service", description = "API для заказа билетов")
interface TicketSalesApi {
    @Operation(summary = "Создание заказа на покупку билета")
    @ApiResponses(
        ApiResponse(
            description = "Успешный запрос",
            responseCode = "201",
        ),
        ApiResponse(
            description = "Некорректный запрос",
            responseCode = "400",
        )
    )
    fun createOrder(
        @Schema(
            description = "Станция отправления",
            defaultValue = "Moscow"
        )
        @NotBlank
        fromStation: String = "Moscow",
        @Schema(
            description = "Станция прибытия",
            defaultValue = "Saint-Petersburg"
        )
        @NotBlank
        toStation: String = "Saint-Petersburg"
    ): ResponseEntity<String>

    @Operation(summary = "Показать все заказы на покупку билета")
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
    fun getOrders(): ResponseEntity<List<String>>


    @Operation(summary = "Предоставление информации о заказе на покупку билета")
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
    fun getOrder(
        @Schema(
            description = "ID заказа",
            defaultValue = "123e4567-e89b-12d3-a456-426614174000"
        )
        @Pattern(
            regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Указанное значение не является UUID"
        )
        orderId: String ="123e4567-e89b-12d3-a456-426614174000"
    ): ResponseEntity<String>
}
