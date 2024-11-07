package com.joonseolee.security.api.method

import com.joonseolee.security.api.Account
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.authorization.method.MethodInvocationResult
import org.springframework.security.core.Authentication
import java.util.function.Supplier

class MyPostAuthorizationManager : AuthorizationManager<MethodInvocationResult> {
    override fun check(
        authentication: Supplier<Authentication>?,
        `object`: MethodInvocationResult?
    ): AuthorizationDecision {
        val auth = authentication?.get()
        if (auth is AnonymousAuthenticationToken) {
            return AuthorizationDecision(false)
        }

        val account = `object`?.result as Account
        val isGranted = account.owner == auth!!.name

        return AuthorizationDecision(isGranted)
    }
}