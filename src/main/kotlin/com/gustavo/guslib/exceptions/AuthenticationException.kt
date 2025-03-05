package com.gustavo.guslib.exceptions

class AuthenticationException(override val message: String, val errorCode: String): Exception() {
}