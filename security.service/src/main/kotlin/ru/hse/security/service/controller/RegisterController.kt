package ru.hse.security.service.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.hse.security.service.api.SecurityServiceApi
import ru.hse.security.service.service.BuyerService
import kotlin.io.encoding.ExperimentalEncodingApi

@RestController
@RequestMapping
@ExperimentalEncodingApi
class RegisterController(
    private val userService: BuyerService
) : SecurityServiceApi {
    @PostMapping("/register")
    override fun register(@RequestParam username: String, @RequestParam email: String, @RequestParam password: String) =
        userService.registerNewUser(username, email, password)

    @PostMapping("/login")
    override fun login(@RequestParam username: String, @RequestParam password: String): ResponseEntity<String> {
        return try {
            userService.login(username, password)
        } catch (e: Exception) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.message)
        }
    }

    @GetMapping("/getUserInfo")
    override fun getUserInfo() =
        "Username: ${SecurityContextHolder.getContext().authentication.name} \n " +
                "Password: ${SecurityContextHolder.getContext().authentication.credentials} \n " +
                "JWT token: ${SecurityContextHolder.getContext().authentication.credentials} \n "
}
