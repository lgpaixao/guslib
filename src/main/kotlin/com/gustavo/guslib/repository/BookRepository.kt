package com.gustavo.guslib.repository

import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.model.BookModel
import com.gustavo.guslib.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BookRepository: JpaRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
    //fun findAll(pageable: Pageable): Page<BookModel>
}