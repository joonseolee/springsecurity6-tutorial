package com.joonseolee.security.api

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * CustomAuthenticationProvider 로직이 실패할경우 이쪽에서 처리
 * SecurityConfig 에 순서대로 선언함
 */
class CustomAuthenticationProvider2(
    private val userDetailsService: CustomUserDetailsService,
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val loginId = authentication!!.name
        val password = authentication.credentials as String

        // 아이디 검증
        val user: UserDetails =
            userDetailsService.loadUserByUsername(loginId)
                ?: throw UsernameNotFoundException("cannot find")

        // 패스워드 검증

        return UsernamePasswordAuthenticationToken(
            user.username,
            user.password,
            user.authorities,
        )
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication!!.isAssignableFrom(UsernamePasswordAuthenticationToken::class.java)
    }
}
