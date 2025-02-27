package com.gustavo.guslib.exceptions

class BadRequestException(override val message: String, val errorCode: String): Exception() {
}