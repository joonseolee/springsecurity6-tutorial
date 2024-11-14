package com.joonseolee.practical.config.security

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.WebAuthenticationDetails

class FormAuthenticationDetails(
    private val request: HttpServletRequest,
) : WebAuthenticationDetails(request) {
    val secretKey: String? = request.getParameter("secret_key")
    val saying: String? = request.getParameter("saying")
}