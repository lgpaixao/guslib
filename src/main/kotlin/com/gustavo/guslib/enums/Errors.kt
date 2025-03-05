package com.gustavo.guslib.enums

enum class Errors(val code: String, val message: String) {
    GL001("GL-001", "Invalid Request"),
    Gl101("GL-001", "Book [%s] does not exist"),
    GL102("GL-102", "Cannot update book with Status[%s"),
    GL201("GL-002", "Customer [%s] does not exist"),
    GL301("GL-301", "Book [%s] not available")
}