package com.joonseolee.practical.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.access.AccessDeniedHandler

class FormAccessDeniedHandler(
    private val errorPage: String
) : AccessDeniedHandler {

    private val redirectStrategy = DefaultRedirectStrategy()

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val deniedUrl = "$errorPage?exception=${accessDeniedException.message}"
        redirectStrategy.sendRedirect(request, response, deniedUrl)
    }
}