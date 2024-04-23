package com.joonseolee.security.api

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.context.DelegatingSecurityContextRepository
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

class CustomAuthenticationFilter(http: HttpSecurity) :
    AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/login", "GET")) {
    init {
        setSecurityContextRepository(getSecurityContextRepository(http))
    }

    private fun getSecurityContextRepository(http: HttpSecurity): SecurityContextRepository {
        var securityContextRepository = http.getSharedObject(SecurityContextRepository::class.java)
        if (securityContextRepository == null) {
            securityContextRepository = DelegatingSecurityContextRepository(
                HttpSessionSecurityContextRepository(),
                RequestAttributeSecurityContextRepository())
        }

        return securityContextRepository
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val username = request!!.getParameter("username")
        val password = request.getParameter("password")

        val token = UsernamePasswordAuthenticationToken(username, password)

        return this.authenticationManager.authenticate(token)
    }
}