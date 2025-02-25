package com.gustavo.guslib.controller.request

import com.gustavo.guslib.enums.BookStatus
import java.math.BigDecimal

data class PutBookRequest (
    var name: String?,
    var price: BigDecimal?
) {
}