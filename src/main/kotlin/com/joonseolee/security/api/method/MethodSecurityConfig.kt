package com.joonseolee.security.api.method

import org.aopalliance.intercept.MethodInvocation
import org.springframework.aop.Advisor
import org.springframework.aop.Pointcut
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.aop.support.ComposablePointcut
import org.springframework.aop.support.Pointcuts
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import org.springframework.security.authorization.AuthorityAuthorizationManager
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity

@EnableMethodSecurity(prePostEnabled = false)
@Configuration
class MethodSecurityConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun pointCutAdvisor(): Advisor {
        val aspectJExpressionPointcut = AspectJExpressionPointcut()
        aspectJExpressionPointcut.expression = "execution(* com.joonseolee.security.api.DataService.getUser(..))"
        val manager = AuthorityAuthorizationManager.hasAuthority<MethodInvocation>("MYPREFIX_USER");

        return AuthorizationManagerBeforeMethodInterceptor(aspectJExpressionPointcut, manager)
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun pointCutAdvisor2(): Advisor {
        val pointcut1 = AspectJExpressionPointcut()
        pointcut1.expression = "execution(* com.joonseolee.security.api.DataService.getUser(..))"

        val pointcut2 = AspectJExpressionPointcut()
        pointcut2.expression = "execution(* com.joonseolee.security.api.DataService.getOwner(..))"

        val composablePointcut = ComposablePointcut(pointcut2 as Pointcut)
        composablePointcut.union(pointcut1 as Pointcut)

        val manager = AuthorityAuthorizationManager.hasAuthority<MethodInvocation>("MYPREFIX_USER");
        // GrantedAuthorityDefaults 빈을 읽지못함... 애초에 객체를 새로 생성하기때문에 그런듯함...
        // 무조건 hasAuthority 를 사용하는 방식으로 해야할듯 !
//        val manager = AuthorityAuthorizationManager.hasRole<MethodInvocation>("USER")

        return AuthorizationManagerBeforeMethodInterceptor(composablePointcut, manager)
    }
}