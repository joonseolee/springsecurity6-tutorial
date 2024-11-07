package com.joonseolee.security.api.method

import org.springframework.aop.Advisor
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import org.springframework.security.authorization.method.AuthorizationManagerAfterMethodInterceptor
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
class MethodSecurityConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun preAuthorize(): Advisor {
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(MyPreAuthorizationManager())
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun postAuthorize(): Advisor {
        return AuthorizationManagerAfterMethodInterceptor.postAuthorize(MyPostAuthorizationManager())
    }
}