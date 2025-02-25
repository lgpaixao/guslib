package com.gustavo.guslib.controller.response

import com.gustavo.guslib.enums.CustomerStatus
import jakarta.persistence.*

data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus?
) {
}
