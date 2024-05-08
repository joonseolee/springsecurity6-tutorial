package com.joonseolee.security.api

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.filter.OncePerRequestFilter

class CsrfCookieFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val attribute = request.getAttribute("_csrf")
        if (attribute is CsrfToken) {
            attribute.token
        }

        filterChain.doFilter(request, response)
    }
}