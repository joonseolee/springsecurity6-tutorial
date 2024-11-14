package com.joonseolee.practical.config.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.web.authentication.WebAuthenticationDetails

@Configuration
class FormAuthenticationDetailsSource : AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    override fun buildDetails(request: HttpServletRequest): WebAuthenticationDetails {
        return FormAuthenticationDetails(request)
    }
}