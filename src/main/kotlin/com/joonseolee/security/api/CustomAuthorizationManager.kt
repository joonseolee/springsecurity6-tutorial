package com.joonseolee.security.api

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import java.util.function.Supplier

class CustomAuthorizationManager : AuthorizationManager<RequestAuthorizationContext> {

    override fun check(
        authentication: Supplier<Authentication>?,
        `object`: RequestAuthorizationContext?
    ): AuthorizationDecision {
        val auth = authentication?.get()

        if (auth == null || !auth.isAuthenticated || auth is AnonymousAuthenticationToken) {
            return AuthorizationDecision(false)
        }

        val hasRequiredRole = auth.authorities.stream()
            .anyMatch { REQUIRED_ROLE == it.authority }

        return AuthorizationDecision(hasRequiredRole)
    }

    companion object {
        private const val REQUIRED_ROLE = "ROLE_SECURE"
    }
}