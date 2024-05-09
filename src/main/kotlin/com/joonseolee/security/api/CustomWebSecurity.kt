package com.joonseolee.security.api

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomWebSecurity {

    fun check(authentication: Authentication, request: HttpServletRequest): Boolean {
        return authentication.isAuthenticated
    }
}