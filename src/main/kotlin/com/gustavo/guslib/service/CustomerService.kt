package com.gustavo.guslib.service

import com.gustavo.guslib.enums.CustomerStatus
import com.gustavo.guslib.enums.Errors
import com.gustavo.guslib.enums.Profile
import com.gustavo.guslib.exceptions.NotFoundException
import com.gustavo.guslib.model.CustomerModel
import com.gustavo.guslib.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun getAll(name: String?): List<CustomerModel> {
        name?.let { return customerRepository.findByNameContaining(it) }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        val customerCopy = customer.copy(
            roles = setOf(Profile.CUSTOMER),
            password = bCryptPasswordEncoder.encode(customer.password)
        )
        customerRepository.save(customerCopy)
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

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }


}