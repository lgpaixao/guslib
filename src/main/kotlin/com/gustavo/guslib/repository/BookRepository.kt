package com.gustavo.guslib.repository

import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.model.BookModel
import com.gustavo.guslib.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus): List<BookModel>

}