package com.gustavo.guslib.controller

import com.gustavo.guslib.controller.request.PostBookRequest
import com.gustavo.guslib.controller.request.PutBookRequest
import com.gustavo.guslib.extension.toBookModel
import com.gustavo.guslib.model.BookModel
import com.gustavo.guslib.service.BookService
import com.gustavo.guslib.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.awt.print.Book

@RestController
@RequestMapping("/book")
class BookController (
    val customerService: CustomerService,
    val bookService: BookService
){



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest){
        val customer = customerService.getById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookModel {
        return bookService.findById(id)
    }

    @GetMapping
    fun findAll(): List<BookModel>{
        return bookService.findAll()
    }

    @GetMapping("/active")
    fun findActives(): List<BookModel>{
        return bookService.findActives()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT )
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest): BookModel {
        val bookSaved = bookService.findById(id)
        return bookService.update(book.toBookModel(bookSaved))
    }
}