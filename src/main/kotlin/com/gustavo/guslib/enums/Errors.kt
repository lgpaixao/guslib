package com.gustavo.guslib.enums

enum class Errors(val code: String, val message: String) {
    Gl001("GL-001", "Book [%s] does not exist"),
    GL002("GL-002", "Customer [%s] does not exist")
}