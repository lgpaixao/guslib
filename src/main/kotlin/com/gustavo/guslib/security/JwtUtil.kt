package com.gustavo.guslib.security

import com.gustavo.guslib.exceptions.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
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

    fun isValid(token: String): Boolean {
        val claims = getClaims(token)
        if(claims.subject == null || claims.expiration == null || Date().after(claims.expiration)){
            return false
        } else {
            return true
        }
    }

    private fun getClaims(token: String): Claims {
        try {
            val secretKey: SecretKey = Keys.hmacShaKeyFor(secret!!.toByteArray())
            return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (ex: Exception) {
            throw AuthenticationException("Token Invalido", "999")
        }
    }

    fun getSubject(token: String): String? {
        return getClaims(token).subject
    }
}