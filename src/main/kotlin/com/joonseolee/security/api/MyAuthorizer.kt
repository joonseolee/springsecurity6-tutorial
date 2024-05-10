package com.joonseolee.security.api

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.stereotype.Component

@Component
class MyAuthorizer {

    fun isUser(root: MethodSecurityExpressionOperations): Boolean {
        return root.hasAuthority("ROLE_USER")
    }
}