package com.gustavo.guslib.repository

import com.gustavo.guslib.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository: CrudRepository<PurchaseModel, Int> {
    fun findByCustomerId(customerId: Int): List<PurchaseModel>

}
