package com.gustavo.guslib.controller

import com.gustavo.guslib.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
) {

    @GetMapping("/report")
    fun getAll(): String {
        return "This a Report. Only admin can see it"
    }

}