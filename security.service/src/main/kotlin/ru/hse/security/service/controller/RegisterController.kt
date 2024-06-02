package ru.hse.security.service.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.hse.security.service.model.AuthenticationRequest
import ru.hse.security.service.model.Buyer
import ru.hse.security.service.service.BuyerService
import kotlin.io.encoding.ExperimentalEncodingApi

@RestController
@RequestMapping
@ExperimentalEncodingApi
class RegisterController(
    private val userService: BuyerService
) {

    @PostMapping("register")
    fun register(@RequestBody user: Buyer) = userService.registerNewUser(user)

    @PostMapping("login")
    fun login(@RequestBody authenticationRequest: AuthenticationRequest) =
        userService.login(authenticationRequest)
}