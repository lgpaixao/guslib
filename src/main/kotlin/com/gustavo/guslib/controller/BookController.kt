package com.gustavo.guslib.controller

import com.gustavo.guslib.controller.request.PostBookRequest
import com.gustavo.guslib.controller.request.PutBookRequest
import com.gustavo.guslib.controller.response.BookResponse
import com.gustavo.guslib.controller.response.PageResponse
import com.gustavo.guslib.extension.toBookModel
import com.gustavo.guslib.extension.toPageResponse
import com.gustavo.guslib.extension.toResponse
import com.gustavo.guslib.service.BookService
import com.gustavo.guslib.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.web.PageableDefault
import org.springframework.data.domain.Pageable
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

@RestController
@RequestMapping("/books")
class BookController (
    private val customerService: CustomerService,
    private val bookService: BookService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostBookRequest){
        val customer = customerService.findById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size=10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.findAll(pageable).map{it.toResponse()}.toPageResponse()
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size=10) pageable: Pageable): Page<BookResponse> {
        return bookService.findActives(pageable).map{it.toResponse()}
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT )
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest): BookResponse {
        val bookSaved = bookService.findById(id)
        return bookService.update(book.toBookModel(bookSaved)).toResponse()
    }
}