package com.gustavo.guslib.service

import com.gustavo.guslib.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let { return customers.filter { it.name!!.contains(name, true) } }
        return customers
    }

    fun create(customer: CustomerModel) {
        var id = if (customers.isEmpty()) {
            1
        } else {
            customers.last().id!!.toInt() + 1
        }.toString()
        customers.add(CustomerModel(id, customer.name, customer.email))

        println(customer)
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.first { it.id == id }
    }

    fun update(customer: CustomerModel) {
        customers.first { it.id == customer.id }.let {
            it.name = customer.name
            it.email = customer.email}
    }

    fun delete(id:String){
        customers.removeIf { it.id == id }
    }


}