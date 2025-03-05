package com.gustavo.guslib.exceptions

class BookNotAvailableException(override val message: String, val errorCode: String): Exception() {
}