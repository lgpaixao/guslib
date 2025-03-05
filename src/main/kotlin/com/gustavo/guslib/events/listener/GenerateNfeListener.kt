package com.gustavo.guslib.events.listener

import com.gustavo.guslib.events.PurchaseEvent
import com.gustavo.guslib.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateNfeListener (
    private val purchaseService: PurchaseService
) {

    @Async
    @EventListener
    fun list(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)
        purchaseService.update(purchaseModel)
    }
}