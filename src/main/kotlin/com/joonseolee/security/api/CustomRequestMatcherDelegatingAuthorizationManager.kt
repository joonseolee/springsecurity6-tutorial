package com.joonseolee.security.api

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager
import org.springframework.security.web.util.matcher.RequestMatcherEntry
import java.util.function.Supplier

class CustomRequestMatcherDelegatingAuthorizationManager(
    private val mappings: List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>>
) : AuthorizationManager<RequestAuthorizationContext> {

    private val manager: RequestMatcherDelegatingAuthorizationManager =
        RequestMatcherDelegatingAuthorizationManager.builder().mappings { it.addAll(mappings) }.build()

    override fun check(
        authentication: Supplier<Authentication>?,
        `object`: RequestAuthorizationContext?
    ): AuthorizationDecision? {
        return manager.check(authentication, `object`?.request)
    }
}