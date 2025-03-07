package com.gustavo.guslib.config

import com.gustavo.guslib.enums.Roles
import com.gustavo.guslib.repository.CustomerRepository
import com.gustavo.guslib.security.AuthenticationFilter
import com.gustavo.guslib.security.AuthorizationFilter
import com.gustavo.guslib.security.CustomAuthenticationEntryPoint
import com.gustavo.guslib.security.JwtUtil
import com.gustavo.guslib.service.UserDetailsCustomService
import org.springframework.web.filter.CorsFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
class SecurityConfig(
    private val customerRepository: CustomerRepository,
    private val userDetails: UserDetailsCustomService,
    private val jwtUtil: JwtUtil,
    private val customEntrypoint: CustomAuthenticationEntryPoint
) {

    private val PUBLIC_MATCHERS = arrayOf<String>(
        "/books"
    )

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customers"
    )


    private val ADMIN_MATCHERS = arrayOf(
        "/admin/**"
    )

    private val SWAGGER_MATCHERS = arrayOf(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity, authenticationManager: AuthenticationManager): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(*PUBLIC_MATCHERS).permitAll()
                auth.requestMatchers(*SWAGGER_MATCHERS).permitAll()
                auth.requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                auth.requestMatchers(*ADMIN_MATCHERS).hasAuthority(Roles.ADMIN.description)
                auth.anyRequest().authenticated()
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

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

        http.exceptionHandling { it.authenticationEntryPoint(customEntrypoint) }


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

    @Bean
    fun corsConfig(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOriginPattern("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}