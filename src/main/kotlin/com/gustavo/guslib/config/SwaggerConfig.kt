package com.gustavo.guslib.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Profile

@Profile("!prod")
@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "GUSLIB",
        version = "v1",
        description = "Documentação da API"
    )
)
class SwaggerConfig {

    @Bean
    fun api(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("public")
            .packagesToScan("com.gustavo.guslib.controller")
            .build()
    }
}