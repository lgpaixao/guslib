package com.gustavo.guslib.service

import com.gustavo.guslib.enums.Errors
import com.gustavo.guslib.events.PurchaseEvent
import com.gustavo.guslib.exceptions.BookNotAvailableException
import com.gustavo.guslib.model.PurchaseModel
import com.gustavo.guslib.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val bookService: BookService
) {
    fun create(purchaseModel: PurchaseModel){

        purchaseModel.books.map{
            if (!bookService.isActive(it)){
                throw BookNotAvailableException(Errors.GL301.message, Errors.GL301.code)
            }
        }

        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }

    fun getById(customerId: Int): List<PurchaseModel> {
        return purchaseRepository.findByCustomerId(customerId)
    }
}
