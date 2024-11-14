package com.joonseolee.practical.config.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.SavedRequest

@Configuration
class FormAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    private val requestCache = HttpSessionRequestCache()
    private val redirectStrategy = DefaultRedirectStrategy()


    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        defaultTargetUrl = "/"
        val savedRequest: SavedRequest? = requestCache.getRequest(request, response)
        if (savedRequest != null) {
            val redirectUrl = savedRequest.redirectUrl
            redirectStrategy.sendRedirect(request, response, redirectUrl)
            return
        }

        redirectStrategy.sendRedirect(request, response, defaultTargetUrl)
    }
}