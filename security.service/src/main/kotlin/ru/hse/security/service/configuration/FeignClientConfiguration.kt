package ru.hse.security.service.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.cloud.openfeign.EnableFeignClients

@Configuration
@EnableFeignClients(basePackages = ["ru.hse.security.service.client.rest.api"])
class FeignClientConfiguration {
}