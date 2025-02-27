package com.gustavo.guslib.service

import com.gustavo.guslib.enums.BookStatus
import com.gustavo.guslib.enums.Errors
import com.gustavo.guslib.exceptions.NotFoundException
import com.gustavo.guslib.model.BookModel
import com.gustavo.guslib.model.CustomerModel
import com.gustavo.guslib.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService (
    val bookRepository: BookRepository
){
    fun create(book: BookModel) =
        bookRepository.save(book)


    fun findById(id: Int): BookModel =
        bookRepository.findById(id).orElseThrow {NotFoundException(Errors.Gl101.message.format(id), Errors.Gl101.code)}


    fun findAll(pageable: Pageable): Page<BookModel> =
        bookRepository.findAll(pageable)


    fun findActives(pageable: Pageable): Page<BookModel> =
        bookRepository.findByStatus(BookStatus.ATIVO, pageable)

    fun delete(id: Int) {
        val book = findById(id)

        book.status = BookStatus.DELETADO

        update(book)
    }

    fun update(book: BookModel): BookModel {
        return bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {book.status = BookStatus.DELETADO}
        bookRepository.saveAll(books)
    }



}
