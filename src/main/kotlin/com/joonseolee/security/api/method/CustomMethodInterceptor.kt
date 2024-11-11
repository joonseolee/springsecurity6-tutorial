package com.joonseolee.security.api.method

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.context.SecurityContextHolder

class CustomMethodInterceptor(
    private val authorizationManager: AuthorizationManager<MethodInvocation>
) : MethodInterceptor {

    override fun invoke(invocation: MethodInvocation): Any? {
        val authentication = SecurityContextHolder.getContextHolderStrategy().context.authentication
        val authenticationDecision = authorizationManager.check({ authentication }, invocation)
        if (authenticationDecision!!.isGranted) {
            return invocation.proceed()
        }

        throw AccessDeniedException("Access Denied")
    }
}