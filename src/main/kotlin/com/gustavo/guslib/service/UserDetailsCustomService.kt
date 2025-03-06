package com.gustavo.guslib.service

import com.gustavo.guslib.exceptions.AuthenticationException
import com.gustavo.guslib.repository.CustomerRepository
import com.gustavo.guslib.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String?): UserDetails? {
        val customer = customerRepository.findById(id!!.toInt())
            .orElseThrow { AuthenticationException("Usuário não encontrado", "999") }

        return UserCustomDetails(customer)
    }
}