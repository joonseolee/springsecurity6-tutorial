package com.joonseolee.security.api.method

import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import java.util.function.Supplier

class MyPreAuthorizationManager : AuthorizationManager<MethodInvocation> {

    override fun check(authentication: Supplier<Authentication>?, `object`: MethodInvocation?): AuthorizationDecision {
        val auth = authentication?.get()
        if (auth is AnonymousAuthenticationToken) {
            return AuthorizationDecision(false)
        }

        return AuthorizationDecision(auth!!.isAuthenticated)
    }
}