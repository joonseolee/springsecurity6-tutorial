package com.joonseolee.security.api

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

class CustomAuthenticationProvider : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        val loginId = authentication!!.name
        val password = authentication.credentials as String

        return UsernamePasswordAuthenticationToken(loginId, password, listOf(SimpleGrantedAuthority("ROLE_ADMIN")))
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication!!.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}