package com.gustavo.guslib.extension

import com.gustavo.guslib.controller.request.PostBookRequest
import com.gustavo.guslib.controller.request.PostCustomerRequest
import com.gustavo.guslib.controller.request.PutBookRequest
import com.gustavo.guslib.controller.request.PutCustomerRequest
import com.gustavo.guslib.controller.response.BookResponse
import com.gustavo.guslib.controller.response.CustomerResponse
import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.model.BookModel
import com.gustavo.guslib.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email=this.email, status= CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel{
    return CustomerModel(id = previousValue.id, name = this.name, email=this.email, status = CustomerStatus.ATIVO)
}
fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel{
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel{
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(id = this.id,
        name = this.name,
        email = this.email,
        status = this.status)
}

fun BookModel.toResponse(): BookResponse {
    return BookResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        status = this.status,
        customer = this.customer)
}