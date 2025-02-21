package com.gustavo.guslib.extension

import com.gustavo.guslib.controller.request.PostCustomerRequest
import com.gustavo.guslib.controller.request.PutCustomerRequest
import com.gustavo.guslib.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel{
    return CustomerModel(name = this.name, email=this.name)
}

fun PutCustomerRequest.toCustomerModel(id: String): CustomerModel{
    return CustomerModel(id = id, name = this.name, email=this.name)
}