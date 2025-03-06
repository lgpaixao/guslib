package com.gustavo.guslib.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil {

    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    fun generateToken(id: Int): String { // 1 hora

        val secretKey: SecretKey = Keys.hmacShaKeyFor(secret!!.toByteArray())
        val expirationTime = Date(System.currentTimeMillis() + expiration!!)

        return Jwts.builder()
            .subject(id.toString())
            .expiration(expirationTime)
            .signWith(secretKey)
            .compact()
    }
}