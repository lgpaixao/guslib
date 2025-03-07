package com.gustavo.guslib.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gustavo.guslib.controller.response.ErrorResponse
import com.gustavo.guslib.enums.Errors
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response?.contentType = "application/json"
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
        val errorResponse = ErrorResponse(
            HttpStatus.UNAUTHORIZED.value(), Errors.GL000.message, Errors.GL000.code, null
        )

        response?.outputStream?.print(jacksonObjectMapper().writeValueAsString(errorResponse))
    }
}