package com.gustavo.guslib.controller.request

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.model.CustomerModel
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(

    @field:NotEmpty(message = "Nome não pode ser vazio")
    var name: String,

    @field:Email(message = "Email deve ser válido")
    var email: String
) {
    fun toCustomerModel(): CustomerModel{
        return CustomerModel(name = this.name, email=this.email, status = CustomerStatus.ATIVO)
    }
}