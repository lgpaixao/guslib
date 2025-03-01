package com.gustavo.guslib.validation

import com.gustavo.guslib.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmaillAvailableValidator(var customerService: CustomerService): ConstraintValidator<EmaillAvailable, String> {
    override fun isValid(value: String?, p1: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()){
            return false
        }
        return customerService.emailAvailable(value)
    }

}
