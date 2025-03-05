package com.gustavo.guslib.events.listener

import com.gustavo.guslib.events.PurchaseEvent
import com.gustavo.guslib.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class GenerateSoldBookListener (
    private val bookService: BookService
) {

    @Async
    @EventListener
    fun list(purchaseEvent: PurchaseEvent){
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}