package com.gustavo.guslib.exceptions

class NotFoundException(override val message: String, val errorCode: String): Exception() {
}