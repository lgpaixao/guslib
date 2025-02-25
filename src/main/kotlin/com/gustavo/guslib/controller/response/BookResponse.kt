package com.gustavo.guslib.controller.response

import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.model.CustomerModel
import jakarta.persistence.*
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,
    var name: String,
    var price: BigDecimal,
    var customer: CustomerModel? = null,
    var status: BookStatus?
) {
}
