package com.joonseolee.security.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authorization.AuthenticatedAuthorizationManager
import org.springframework.security.authorization.AuthorityAuthorizationManager
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.security.web.util.matcher.AnyRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcherEntry
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .anyRequest().access(authorizationManager(null))
            }
            .formLogin(Customizer.withDefaults())
            .csrf {
                it
                    .disable()
            }

        return http.build()
    }

    @Bean
    fun authorizationManager(introspector: HandlerMappingIntrospector?): AuthorizationManager<RequestAuthorizationContext> {
        val requestMatcherEntry1 = RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>(
            MvcRequestMatcher(introspector, "/user"), AuthorityAuthorizationManager.hasAuthority("ROLE_USER"))

        val requestMatcherEntry2 = RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>(
            MvcRequestMatcher(introspector, "/db"), AuthorityAuthorizationManager.hasAuthority("ROLE_DB"))

        val requestMatcherEntry3 = RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>(
            MvcRequestMatcher(introspector, "/admin"), AuthorityAuthorizationManager.hasAuthority("ROLE_ADMIN"))

        val requestMatcherEntry4 = RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>(
            AnyRequestMatcher.INSTANCE, AuthenticatedAuthorizationManager())

        return CustomRequestMatcherDelegatingAuthorizationManager(
            listOf(requestMatcherEntry1,
                requestMatcherEntry2,
                requestMatcherEntry3,
                requestMatcherEntry4))
    }

    @Bean
    fun roleHierarchy(): RoleHierarchy {
        return RoleHierarchyImpl().apply {
            setHierarchy("MYPREFIX_ADMIN > MYPREFIX_DB\n" +
                "MYPREFIX_DB > MYPREFIX_USER\n" +
                "MYPREFIX_USER > MYPREFIX_ANONYMOUS"
            )
        }
    }

    /**
     * 해당 빈이 있을경우 application.yml 파일보다 우선순위가 높다
     */
    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withUsername("user").password("{noop}1111").roles("USER").build()
        val db = User.withUsername("db").password("{noop}1111").roles("DB").build()
        val admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN", "SECURE").build()
        val nom = User.withUsername("nom").password("{noop}1111").roles("ADMIN", "SECURE").build()
        return InMemoryUserDetailsManager(user, db, admin, nom)
    }
}
