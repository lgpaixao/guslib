package com.gustavo.guslib.config

import com.gustavo.guslib.repository.CustomerRepository
import com.gustavo.guslib.security.AuthenticationFilter
import com.gustavo.guslib.security.JwtUtil
import com.gustavo.guslib.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import com.gustavo.guslib.security.AuthorizationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customerRepository: CustomerRepository,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil
) {

    private val PUBLIC_MATCHERS = arrayOf<String>()

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customer"
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(*PUBLIC_MATCHERS).permitAll()
                auth.requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                auth.anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.addFilter(
            AuthenticationFilter(
                authenticationManager,
                customerRepository,
                jwtUtil
            )
        )
        http.addFilter(
            AuthorizationFilter(
                authenticationManager,
                jwtUtil,
                userDetails
            )
        )

        return http.build()
    }


    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetails) // Substitui o antigo configure(auth)
        provider.setPasswordEncoder(bCryptPasswordEncoder())
        return provider
    }

}