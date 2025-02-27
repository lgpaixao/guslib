package com.gustavo.guslib.service

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.enums.Errors
import com.gustavo.guslib.exceptions.NotFoundException
import com.gustavo.guslib.model.CustomerModel
import com.gustavo.guslib.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {
    fun getAll(name: String?): List<CustomerModel> {
        name?.let { return customerRepository.findByNameContaining(it) }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow {NotFoundException(Errors.GL201.message.format(id), Errors.GL201.code)}
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int){
        val customer = findById(id)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }


}