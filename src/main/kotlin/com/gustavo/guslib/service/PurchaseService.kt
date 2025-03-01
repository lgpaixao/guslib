package com.gustavo.guslib.service

import com.gustavo.guslib.model.PurchaseModel
import com.gustavo.guslib.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun create(purchaseModel: PurchaseModel){
        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }
}
