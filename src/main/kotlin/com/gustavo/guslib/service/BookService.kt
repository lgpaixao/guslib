package com.gustavo.guslib.service

import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.model.BookModel
import com.gustavo.guslib.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService (
    val bookRepository: BookRepository
){
    fun create(book: BookModel) =
        bookRepository.save(book)


    fun findById(id: Int): BookModel =
        bookRepository.findById(id).orElseThrow()


    fun findAll(): List<BookModel> =
        bookRepository.findAll().toList()


    fun findActives(): List<BookModel> =
        bookRepository.findByStatus(BookStatus.ATIVO)

    fun delete(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(book: BookModel): BookModel {
        return bookRepository.save(book)
    }


}
