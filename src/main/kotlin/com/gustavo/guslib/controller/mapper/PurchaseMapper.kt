package com.gustavo.guslib.controller.mapper

import com.gustavo.guslib.controller.request.PostPurchaseRequest
import com.gustavo.guslib.model.PurchaseModel
import com.gustavo.guslib.service.BookService
import com.gustavo.guslib.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper (
    private val bookService: BookService,
    private val customerService: CustomerService
){
    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)

        return PurchaseModel(
            customer = customer,
            books = books,
            price = books.sumOf { it.price }
        )


    }
}