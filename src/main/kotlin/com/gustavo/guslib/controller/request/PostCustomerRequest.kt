package com.gustavo.guslib.controller.request

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.model.CustomerModel

data class PostCustomerRequest(
    var name: String,
    var email: String
) {
    fun toCustomerModel(): CustomerModel{
        return CustomerModel(name = this.name, email=this.email, status = CustomerStatus.ATIVO)
    }
}