package com.gustavo.guslib.security

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.model.CustomerModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails (
    val customerModel: CustomerModel
): UserDetails {

    val id: Int = customerModel.id!!

    override fun getAuthorities(): Collection<GrantedAuthority?>? = customerModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()

    override fun getPassword(): String? = customerModel.password

    override fun getUsername(): String? = customerModel.id.toString()

    override fun isEnabled(): Boolean {
        return customerModel.status == CustomerStatus.ATIVO
    }

}