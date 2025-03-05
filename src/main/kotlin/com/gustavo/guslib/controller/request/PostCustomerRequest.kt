package com.gustavo.guslib.controller.request

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.model.CustomerModel
import com.gustavo.guslib.validation.EmaillAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(

    @field:NotEmpty(message = "Nome não pode ser vazio")
    var name: String,

    @field:Email(message = "Email deve ser válido")
    @EmaillAvailable
    var email: String,

    @field:NotEmpty(message = "Senha deve ser informada")
    var password: String
)