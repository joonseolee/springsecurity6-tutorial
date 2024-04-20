package com.joonseolee.security.api

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class CustomAuthenticationProvider : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        val loginId = authentication!!.name
        val password = authentication.credentials as String

        // 아이디 검증
        // 패스워드 검증

        return null
//        return UsernamePasswordAuthenticationToken(
//            loginId,
//            password,
//            listOf(SimpleGrantedAuthority("ROLE_USER")),
//        )
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication!!.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}
